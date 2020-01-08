package smart.com.classroom.util

import io.agora.rtm.*
import jrh.library.common.app.AppConfig
import smart.com.classroom.APPID

class RTMHelper {


    var mRtmClient: RtmClient? = null
    var mRtmChannel: RtmChannel? = null
    var loginStatus = false

    fun initData(token: String, uid: String,channelNameId:String) {
        mRtmClient =RtmClient.createInstance(AppConfig.getContext(), APPID, object : RtmClientListener {
                override fun onTokenExpired() {

                }

                override fun onPeersOnlineStatusChanged(p0: MutableMap<String, Int>?) {
                }

                override fun onConnectionStateChanged(state: Int, reason: Int) {
                }

                override fun onMessageReceived(rtmMessage: RtmMessage?, peerId: String?) {

                }
            })
        login(token, uid,channelNameId)

    }

    /**
     * 创建&&加入频道
     */
    fun createJoinChannel(channelNameId:String){
        mRtmClient?.let {
            /**
             * 创建频道，这里可以接收频道消息
             */
            mRtmChannel = it.createChannel(channelNameId,object :RtmChannelListener{
                override fun onAttributesUpdated(p0: MutableList<RtmChannelAttribute>?) {

                }
                override fun onMessageReceived(p0: RtmMessage?, p1: RtmChannelMember?) {


                }

                override fun onMemberJoined(p0: RtmChannelMember?) {
                }

                override fun onMemberLeft(p0: RtmChannelMember?) {
                }

                override fun onMemberCountUpdated(p0: Int) {
                }
            })

            /**
             * 加入频道
             */
            mRtmChannel?.let {
                it.join(object :ResultCallback<Void>{
                    override fun onSuccess(p0: Void?) {

                    }

                    override fun onFailure(p0: ErrorInfo?) {
                    }
                })
            }

        }


    }


    /**
     * 登陆
     */

    fun login(token: String, uid: String,channelNameId:String) {
        mRtmClient?.let {
            it.login(token, uid, object : ResultCallback<Void> {
                override fun onSuccess(p0: Void?) {

                    loginStatus = true
                    createJoinChannel(channelNameId)
                }

                override fun onFailure(p0: ErrorInfo?) {

                    loginStatus = false
                }
            })
        }
    }

    fun logout(){
        mRtmClient?.let { it.logout(null) }
        mRtmChannel?.release()
    }

    /**
     * 点对点发送消息
     */
    fun sendPeerMessage(dst:String,content:String){
        mRtmClient?.let {
            val message = it.createMessage().apply { text= content }
            var option = SendMessageOptions().apply { enableOfflineMessaging = true }
            it.sendMessageToPeer(dst,message,option,object :ResultCallback<Void>{
                override fun onSuccess(p0: Void?) {

                }

                override fun onFailure(p0: ErrorInfo?) {

                }
            })
        }
    }

    /**
     * 发送频道消息
     */
    fun sendChannelIMessage(msg:String){
        val message = mRtmClient?.createMessage()?.apply { text = msg }
        mRtmChannel?.sendMessage(message,object :ResultCallback<Void>{
            override fun onSuccess(p0: Void?) {

            }

            override fun onFailure(p0: ErrorInfo?) {
            }
        })
    }
}