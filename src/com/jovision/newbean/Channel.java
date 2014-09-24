package com.jovision.newbean;

import java.io.Serializable;

import android.view.SurfaceView;

import com.jovision.Consts;

/**
 * 简单的通道集合类
 * 
 * @author neo
 * 
 */
public class Channel implements Serializable {

	private static final long serialVersionUID = -2416035263023889605L;

	private final static String TAG = "Channel";
	/** 窗口索引 */
	private int index;
	/** 设备通道，从 0 开始 */
	private int channel;
	/** 通道昵称 */
	public String channelName = "";
	private boolean isConnecting;
	private boolean isConnected;
	private boolean isRemotePlay;
	private boolean isConfigChannel;
	private SurfaceView surfaceView;
	private Device parent;
	private boolean isAuto = false;// 是否开启自动巡航
	private boolean isPause = false;// 是否暂停视频
	private boolean isVoiceCall = false;// 是否正在对讲
	private boolean surfaceCreated = false;// surface是否已经创建
	private boolean isSendCMD = false;// 是否只发关键帧

	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("{\"").append("index").append("\"").append(":")
				.append(index).append(",").append("\"").append("channel")
				.append("\"").append(":").append(channel).append(",")
				.append("\"").append("channelName").append("\"").append(":")
				.append("\"").append(channelName).append("\"}");
		// .append("\"").append("isConnecting").append("\"").append(":").append("\"").append(isConnecting).append("\",")
		// .append("\"").append("isConnected").append("\"").append(":").append("\"").append(isConnected).append("\",")
		// .append("\"").append("isRemotePlay").append("\"").append(":").append("\"").append(isRemotePlay).append("\",")
		// .append("\"").append("isConfigChannel").append("\"").append(":").append("\"").append(isConfigChannel).append("\",")
		// //
		// .append("\"").append("parent").append("\"").append(":").append("\"").append(parent).append("\",")
		// .append("\"").append("isAuto").append("\"").append(":").append("\"").append(isAuto).append("\",")
		// .append("\"").append("isVoiceCall").append("\"").append(":").append("\"").append(isVoiceCall).append("\",")
		// .append("\"").append("surfaceCreated").append("\"").append(":").append("\"").append(surfaceCreated).append("\",")
		// .append("\"").append("isSendCMD").append("\"").append(":").append("\"").append(isSendCMD).append("\"}");
		// MyLog.v(TAG, sBuilder.toString());
		return sBuilder.toString();
	}

	public Channel(Device device, int index, int channel, boolean isConnected,
			boolean isRemotePlay) {
		this.parent = device;
		this.index = index;
		this.channel = channel;
		this.isConnected = isConnected;
		this.isRemotePlay = isRemotePlay;

		if (Consts.CHANNEL_JY == index) {
			isConfigChannel = true;
		} else {
			isConfigChannel = false;
		}
	}

	public Channel() {
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getChannel() {
		return channel;
	}

	public SurfaceView getSurfaceView() {
		return surfaceView;
	}

	public void setSurfaceView(SurfaceView surfaceView) {
		this.surfaceView = surfaceView;
	}

	public boolean isConnected() {
		return isConnected;
	}

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

	public boolean isRemotePlay() {
		return isRemotePlay;
	}

	public void setRemotePlay(boolean isRemotePlay) {
		this.isRemotePlay = isRemotePlay;
	}

	public boolean isConfigChannel() {
		return isConfigChannel;
	}

	// @Override
	// public String toString() {
	// StringBuilder sBuilder = new StringBuilder(1024);
	// sBuilder.append("Channel-")
	// .append((channel < 0) ? "AP" : channel)
	// .append(": isConnected = ")
	// .append(isConnected)
	// .append(", isRemotePlay: ")
	// .append(isRemotePlay)
	// .append(", surfaceView = ")
	// .append((null != surfaceView) ? surfaceView.hashCode() : "null")
	// .append(", window = ").append(index);
	// return sBuilder.toString();
	// }

	public Device getParent() {
		return parent;
	}

	public boolean isAuto() {
		return isAuto;
	}

	public void setAuto(boolean isAuto) {
		this.isAuto = isAuto;
	}

	public boolean isPause() {
		return isPause;
	}

	public void setPause(boolean isPause) {
		this.isPause = isPause;
	}

	public boolean isConnecting() {
		return isConnecting;
	}

	public void setConnecting(boolean isConnecting) {
		this.isConnecting = isConnecting;
	}

	public boolean isVoiceCall() {
		return isVoiceCall;
	}

	public void setVoiceCall(boolean isVoiceCall) {
		this.isVoiceCall = isVoiceCall;
	}

	public boolean isSurfaceCreated() {
		return surfaceCreated;
	}

	public void setSurfaceCreated(boolean surfaceCreated) {
		this.surfaceCreated = surfaceCreated;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public void setConfigChannel(boolean isConfigChannel) {
		this.isConfigChannel = isConfigChannel;
	}

	public void setParent(Device parent) {
		this.parent = parent;
	}

	public boolean isSendCMD() {
		return isSendCMD;
	}

	public void setSendCMD(boolean isSendCMD) {
		this.isSendCMD = isSendCMD;
	}

}
