/**
 * @author Jason-Zhao-Jie
 *
 */
package ArmyAnt.ThirdParty;

import javax.servlet.ServletRequest;
import com.qq.connect.QQConnectException;
import com.qq.connect.utils.json.JSONException;

import ArmyAnt.Algorithm.TripleMap;

public class TencentQQ {
	public static TencentQQ Connect(ServletRequest sReq) {
		TencentQQ ret = new TencentQQ();
		ret.req = sReq;
		ret.oau = new com.qq.connect.oauth.Oauth();
		String url = "";
		try {
			url = ret.oau.getAuthorizeURL(sReq);
		} catch (QQConnectException e) {
			e.printStackTrace();
			return null;
		}
		ret.reqUrl = url;
		return ret;
	}

	public boolean OnConnectSuccess(String successUrl) {
		try {
			token = oau.getAccessTokenByRequest(req);
		} catch (QQConnectException e) {
			e.printStackTrace();
			token = null;
			return false;
		}
		try {
			openId = new com.qq.connect.api.OpenID(token.getAccessToken()).getUserOpenID();
		} catch (QQConnectException e) {
			e.printStackTrace();
			return false;
		}
		userInfo = new com.qq.connect.api.qzone.UserInfo(token.getAccessToken(), openId);
		topic = new com.qq.connect.api.qzone.Topic(token.getAccessToken(), openId);
		share = new com.qq.connect.api.qzone.Share(token.getAccessToken(), openId);
		blog = new com.qq.connect.api.qzone.Blog(token.getAccessToken(), openId);
		album = new com.qq.connect.api.qzone.Album(token.getAccessToken(), openId);
		pageFans = new com.qq.connect.api.qzone.PageFans(token.getAccessToken(), openId);
		weibo = new com.qq.connect.api.weibo.Weibo(token.getAccessToken(), openId);
		weblogUserInfo = new com.qq.connect.api.weibo.UserInfo(token.getAccessToken(), openId);
		otherUserInfo = new com.qq.connect.api.weibo.OtherUserInfo(token.getAccessToken(), openId);
		idol = new com.qq.connect.api.weibo.Idol(token.getAccessToken(), openId);
		tenPay = new com.qq.connect.api.tenpay.TenpayAddress(token.getAccessToken(), openId);
		return true;
	}

	public String GetRequestUrl() {
		return reqUrl;
	}

	public String GetAccessToken() {
		if (token == null)
			return null;
		return token.getAccessToken();
	}

	public String GetOpenID() {
		return openId;
	}

	public TripleMap<String, Boolean, Object> Qzone_GetUserInfo() {
		if (userInfo == null)
			return null;
		TripleMap<String, Boolean, Object> ret = new TripleMap<String, Boolean, Object>();
		com.qq.connect.javabeans.qzone.UserInfoBean resp = null;
		try {
			resp = userInfo.getUserInfo();
		} catch (QQConnectException e) {
			e.printStackTrace();
			return null;
		}
		ret.Insert("avatar_100", true, resp.getAvatar().getAvatarURL100());
		ret.Insert("avatar_50", true, resp.getAvatar().getAvatarURL50());
		ret.Insert("avatar_30", true, resp.getAvatar().getAvatarURL30());
		ret.Insert("gender", true, resp.getGender());
		ret.Insert("yellowVipLevel", false, resp.getLevel());
		ret.Insert("msg", true, resp.getMsg());
		ret.Insert("nickname", true, resp.getNickname());
		ret.Insert("ret", false, resp.getRet());
		ret.Insert("isYearYellowVip", false, resp.isYellowYearVip() ? 1 : 0);
		return ret;
	}

	public TripleMap<String, Boolean, Object> Qzone_NewTopic(String content, String parameters) {
		if (topic == null)
			return null;
		TripleMap<String, Boolean, Object> ret = new TripleMap<String, Boolean, Object>();
		com.qq.connect.javabeans.GeneralResultBean resp = null;
		try {
			resp = topic.addTopic(content, parameters);
		} catch (QQConnectException e) {
			e.printStackTrace();
			return null;
		}
		ret.Insert("error_code", false, resp.getErrCode());
		ret.Insert("msg", true, resp.getMsg());
		ret.Insert("ret", false, resp.getRet());
		return ret;
	}

	public TripleMap<String, Boolean, Object> Qzone_NewShare(String title, String url, String siteName,
			String srcSiteUrl, String parameters) {
		if (share == null)
			return null;
		TripleMap<String, Boolean, Object> ret = new TripleMap<String, Boolean, Object>();
		com.qq.connect.javabeans.GeneralResultBean resp = null;
		try {
			resp = share.addShare(title, url, siteName, srcSiteUrl, parameters);
		} catch (QQConnectException e) {
			e.printStackTrace();
			return null;
		}
		ret.Insert("error_code", false, resp.getErrCode());
		ret.Insert("msg", true, resp.getMsg());
		ret.Insert("ret", false, resp.getRet());
		return ret;
	}

	public TripleMap<String, Boolean, Object> Qzone_NewBlog(String title, String content) {
		if (blog == null)
			return null;
		TripleMap<String, Boolean, Object> ret = new TripleMap<String, Boolean, Object>();
		com.qq.connect.javabeans.GeneralResultBean resp = null;
		try {
			resp = blog.addBlog(title, content);
		} catch (QQConnectException e) {
			e.printStackTrace();
			return null;
		}
		ret.Insert("error_code", false, resp.getErrCode());
		ret.Insert("msg", true, resp.getMsg());
		ret.Insert("ret", false, resp.getRet());
		return ret;
	}

	public TripleMap<String, Boolean, Object> Qzone_Album_NewAlbum(String name, String parameters) {
		if (album == null)
			return null;
		TripleMap<String, Boolean, Object> ret = new TripleMap<String, Boolean, Object>();
		com.qq.connect.javabeans.qzone.AlbumBean resp = null;
		try {
			resp = album.addAlbum(name, parameters);
		} catch (QQConnectException e) {
			e.printStackTrace();
			return null;
		}
		ret.Insert("id", true, resp.getAlbumID());
		ret.Insert("class_id", true, resp.getClassID());
		ret.Insert("cover_url", true, resp.getCoverurl());
		ret.Insert("create_time", true, resp.getCreateTime());
		ret.Insert("description", true, resp.getDesc());
		ret.Insert("msg", true, resp.getMsg());
		ret.Insert("name", true, resp.getName());
		ret.Insert("picture_num", false, resp.getPicnum());
		ret.Insert("privious", false, resp.getPriv());
		ret.Insert("ret", false, resp.getRet());
		return ret;
	}

	public TripleMap<String, Boolean, Object> Qzone_Album_NewPicture(byte[] picData, String parameters) {
		if (album == null)
			return null;
		TripleMap<String, Boolean, Object> ret = new TripleMap<String, Boolean, Object>();
		com.qq.connect.javabeans.qzone.AlbumPicBean resp = null;
		try {
			resp = album.uploadPic(picData, parameters);
		} catch (QQConnectException e) {
			e.printStackTrace();
			return null;
		}

		ret.Insert("album_id", true, resp.getAlbumID());
		ret.Insert("large_id", true, resp.getLloc());
		ret.Insert("large_url", true, resp.getLargeURL());
		ret.Insert("small_id", true, resp.getSloc());
		ret.Insert("small_url", true, resp.getSmallURL());
		ret.Insert("height", false, resp.getHeight());
		ret.Insert("width", false, resp.getWidth());
		ret.Insert("msg", true, resp.getMsg());
		ret.Insert("ret", false, resp.getRet());
		return ret;
	}

	public TripleMap<String, Boolean, Object> Qzone_Album_GetAlbumList() {
		if (album == null)
			return null;
		TripleMap<String, Boolean, Object> ret = new TripleMap<String, Boolean, Object>();
		java.util.ArrayList<com.qq.connect.javabeans.qzone.AlbumBean> resp = null;
		try {
			resp = album.listAlbum();
		} catch (QQConnectException e) {
			e.printStackTrace();
			return null;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		ret.Insert("length", false, resp.size());
		for (int i = 0; i < resp.size(); i++) {
			ret.Insert(i + "_id", true, resp.get(i).getAlbumID());
			ret.Insert(i + "_class_id", true, resp.get(i).getClassID());
			ret.Insert(i + "_cover_url", true, resp.get(i).getCoverurl());
			ret.Insert(i + "_create_time", true, resp.get(i).getCreateTime());
			ret.Insert(i + "_description", true, resp.get(i).getDesc());
			ret.Insert(i + "_msg", true, resp.get(i).getMsg());
			ret.Insert(i + "_name", true, resp.get(i).getName());
			ret.Insert(i + "_picture_num", false, resp.get(i).getPicnum());
			ret.Insert(i + "_privious", false, resp.get(i).getPriv());
			ret.Insert(i + "_ret", false, resp.get(i).getRet());
		}
		return ret;
	}

	public TripleMap<String, Boolean, Object> Qzone_IsFansOfSomeonePage(String pageQQ) {
		if (pageFans == null)
			return null;
		TripleMap<String, Boolean, Object> ret = new TripleMap<String, Boolean, Object>();
		com.qq.connect.javabeans.qzone.PageFansBean resp = null;
		try {
			resp = pageFans.checkPageFans(pageQQ);
		} catch (QQConnectException e) {
			e.printStackTrace();
			return null;
		}
		ret.Insert("isFans", false, resp.isFans() ? 1 : 0);
		ret.Insert("msg", true, resp.getMsg());
		ret.Insert("ret", false, resp.getRet());
		return ret;
	}

	public TripleMap<String, Boolean, Object> Tenpay_GetAddressInfo(int firstIndex, int maxLength) {
		if (tenPay == null)
			return null;
		TripleMap<String, Boolean, Object> ret = new TripleMap<String, Boolean, Object>();
		com.qq.connect.javabeans.tenpay.TenpayAddressBean resp = null;
		try {
			resp = tenPay.getAddress(firstIndex,maxLength);
		} catch (QQConnectException e) {
			e.printStackTrace();
			return null;
		}
		int retNum = resp.getRetNum();
		java.util.ArrayList<com.qq.connect.javabeans.tenpay.Address> addresses = resp.getAddresses();
		for(int i=0;i<retNum;i++){
			ret.Insert(i + "_address_street", true, addresses.get(i).getAddrStreet());
			ret.Insert(i + "_create_time", true, addresses.get(i).getCreateTime());
			ret.Insert(i + "_index", true, addresses.get(i).getIndex());
			ret.Insert(i + "_last_use_time", true, addresses.get(i).getLastUseTime());
			ret.Insert(i + "_mobile_num", true, addresses.get(i).getMobile());
			ret.Insert(i + "_last_modified_time", true, addresses.get(i).getModTime());
			ret.Insert(i + "_name", true, addresses.get(i).getName());
			ret.Insert(i + "_region_ID", true, addresses.get(i).getRegionID());
			ret.Insert(i + "_tel", true, addresses.get(i).getTel());
			ret.Insert(i + "_used_times", true, addresses.get(i).getUsedCount());
			ret.Insert(i + "_zipcode", false, addresses.get(i).getZipcode());
			
		}
		ret.Insert("length", false, retNum);
		ret.Insert("msg", true, resp.getMsg());
		ret.Insert("ret", false, resp.getRet());
		return ret;
	}
	
	// TODO: Add weibo executing functions

	private ServletRequest req;
	private com.qq.connect.oauth.Oauth oau;
	private String reqUrl;
	com.qq.connect.javabeans.AccessToken token = null;
	String openId = null;

	com.qq.connect.api.qzone.UserInfo userInfo = null;
	com.qq.connect.api.qzone.Topic topic = null;
	com.qq.connect.api.qzone.Share share = null;
	com.qq.connect.api.qzone.Blog blog = null;
	com.qq.connect.api.qzone.Album album = null;
	com.qq.connect.api.qzone.PageFans pageFans = null;

	com.qq.connect.api.weibo.Weibo weibo = null;
	com.qq.connect.api.weibo.UserInfo weblogUserInfo = null;
	com.qq.connect.api.weibo.OtherUserInfo otherUserInfo = null;
	com.qq.connect.api.weibo.Idol idol = null;

	com.qq.connect.api.tenpay.TenpayAddress tenPay = null;
}
