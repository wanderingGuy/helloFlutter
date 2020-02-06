package com.example.hellowandering

import android.os.Bundle
import android.util.Log

import io.flutter.app.FlutterActivity
import io.flutter.plugins.GeneratedPluginRegistrant
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.StandardMessageCodec

class MainActivity : FlutterActivity() {
    private val METHOD_CHANNEL = "com.test/test"
    private val MESSAGE_CHANNEL = "com.test/basicMessageChannel"
    private lateinit var methodChannel: MethodChannel
    lateinit var basicMessageChannel: BasicMessageChannel<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GeneratedPluginRegistrant.registerWith(this)
        initMethodChannel()
        initBasicMessageChannel()

    }


    private fun initMethodChannel() {
        methodChannel = MethodChannel(flutterView, METHOD_CHANNEL)
        methodChannel.setMethodCallHandler { methodCall, result ->
            Log.d("hellowandering", methodCall.method)
            //主线程 不要做耗时任务
            if (methodCall.method.equals("getAppVersionName")) {
                //必须在主线程中调用
                window.decorView.postDelayed({
                    val message = packageManager.getPackageInfo(packageName, 0)
                    result.success(message.versionName)
                }, 2000)

            }
        }
    }

    private fun initBasicMessageChannel() {
        basicMessageChannel = BasicMessageChannel<Any>(flutterView, MESSAGE_CHANNEL, StandardMessageCodec.INSTANCE)
        basicMessageChannel.setMessageHandler { any, reply ->
            //收到从Flutter传来的消息
            Log.d("hellowandering", "basic message channel start")
            val arguments = any as Map<Any, Any>
            //方法名标识
            val lMethod = arguments["method"] as String
            Log.d("hellowandering", "basic message channel :$lMethod")
            val resultMap = HashMap<String, Any>()
            resultMap["message"] = "reply.reply 返回给flutter的数据"
            resultMap["code"] = 200
            //回调 此方法只能使用一次
            reply.reply(resultMap) //相当于返回值
        }
    }

    override fun onBackPressed() {
        //通过method channel 给Flutter发送消息
        methodChannel.invokeMethod("getName", "hah", object : MethodChannel.Result {
            override fun notImplemented() {

            }

            override fun error(p0: String?, p1: String?, p2: Any?) {
            }

            override fun success(result: Any?) {
                Log.d("hellowandering", "method channel, native 收到返回值 $result")
            }
        })
        val message = "{\"method\": \"test2\", \"content\": \"flutter 中的数据\", \"code\": 100}"
//        //通过message channel 给Flutter发送消息
        basicMessageChannel.send(message) {
            Log.d("hellowandering", "mMessageChannellll native 收到回调 $it")
        }
        return
    }
}
