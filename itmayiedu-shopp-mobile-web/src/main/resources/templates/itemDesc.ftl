
<!DOCTYPE html>
<!-- saved from url=(0069)http://m.mi.com/index.html#ac=product&op=view&commodity_id=1152800035 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="author" content="xiaomi">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="apple-touch-icon-precomposed"
	href="http://img01.mifile.cn/m/app/touch-icon.png">
<link rel="shortcut icon" href="http://m.mi.com/favicon.ico"
	type="image/x-icon">
<meta name="description"
	content="手版小米商城客户端可以随时随地参与预约抢购小米手机，轻松下单购买，实时查询订单信息掌握物流状态，小米商城app手机客户端还有新品信息通知推送功能，所有商品信息随身掌握。">
<meta name="keywords" content="小米商城,小米手机,小米商城下载,小米商城app">
<link rel="stylesheet" type="text/css" href="css/order.css">
<title>小米商城-小米官网</title>
<style type="text/css">
object, embed {
	-webkit-animation-duration: .001s;
	-webkit-animation-name: playerInserted;
	-ms-animation-duration: .001s;
	-ms-animation-name: playerInserted;
	-o-animation-duration: .001s;
	-o-animation-name: playerInserted;
	animation-duration: .001s;
	animation-name: playerInserted;
}

@
-webkit-keyframes playerInserted {
	from {opacity: 0.99;
}

to {
	opacity: 1;
}

}
@
-ms-keyframes playerInserted {
	from {opacity: 0.99;
}

to {
	opacity: 1;
}

}
@
-o-keyframes playerInserted {
	from {opacity: 0.99;
}

to {
	opacity: 1;
}

}
@
keyframes playerInserted {
	from {opacity: 0.99;
}

to {
	opacity: 1;
}
}
</style>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    $(document).ready(function (){
    	  $("#bt_sp").click(function (){
    		  var itemId=$("#itemId").val();
    		   $.ajax({url:"addShopping?itemId="+itemId,success:function(result){
    		             alert(result.msg)
    		    }});
    	  })
    });

</script>
<body style="display: block; background: rgb(255, 255, 255);">
	<div id="Cheader" style="">
   <input type="hidden" id="itemId"  value="${item['id']}">
		<div id="header" class="header_03">
			<div class="back">
				<a href="http://m.mi.com/home/" data-stat-id="1c3b2f8d3dcebd60"
					onclick="_msq.push([&#39;trackEvent&#39;, &#39;f086bb7168acc7da-1c3b2f8d3dcebd60&#39;, &#39;/home/&#39;, &#39;pcpid&#39;]);"
					class="arrow">首页</a>
			</div>
			<div class="tit" style="">
				<h3>商品详情</h3>
			</div>
			<div class="nav">
				<ul>
					<li class="cart"><a
						href="http://m.mi.com/index.html#ac=shopping&op=index"
						data-stat-id="96c452cb9824c0df"
						onclick="_msq.push([&#39;trackEvent&#39;, &#39;f086bb7168acc7da-96c452cb9824c0df&#39;, &#39;/index.html#ac=shopping&amp;op=index&#39;, &#39;pcpid&#39;]);">购物车</a>
						<span id="ShoppingCartNum" style="display: none"></span></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="wrapper" class="xm_app">
		<div id="viewport" class="viewport" style="">
			<div id="crumbs" class="crumbs" style="display: none;">
				<ul>
					<li><a href="http://m.mi.com/home/"><span>首页</span></a></li>
					<li><a
						href="http://m.mi.com/index.html#ac=product&op=category">分类</a></li>
					<li><a href="javascript:;">商品详情</a></li>
				</ul>
			</div>
			<div class="product_view">
				<div class="box box_01">
					<div class="product_swipe">
						<!-- 单品介绍图片 -->
						<div class="swipe" id="slider" style="visibility: visible;">
							<div class="swipe-wrap" style="width: 2160px;">
								<div data-index="0"
									style="width: 720px; left: 0px; transition-duration: 0ms; transform: translate(0px, 0px) translateZ(0px);">
									<img width="100%"
										src="https://cdn.cnbj0.fds.api.mi-img.com/b2c-mimall-media/d68da7f79cc9800a34b1b48e1a439e44.jpg"
										alt="小米商城">
								</div>

							</div>
							<div class="swipe-nav">
								<span class="on">&nbsp;</span> <span>&nbsp;</span> <span>&nbsp;</span>
							</div>
						</div>
					</div>
					<div class="product_info">
						<div class="info info_right">
							<h3 class="name">${item['title']}</h3>
							<div class="right">
								<div id="favorite_add" class="favorite_add">
									<span>收藏</span>
								</div>
								<div id="pro_share" class="share">
									<span>分享</span>
								</div>
							</div>
						</div>
						<div class="price">${item['price']}</div>
						<div class="acts"></div>
						<div class="star comment_star">
							<div class="star_box">
								<div class="star_bar">
									<div style="width: 100%" class="star_num"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="product_addCart">
						<div class="tip-success hide">
							<div class="inner">
								<div class="tip-txt">
									<span>商品已加入购物车</span>
								</div>
								<div class="btns">
									<a href="http://m.mi.com/index.html#ac=shopping&op=index"
										class="btn"><span>前往购物车</span></a>
								</div>
							</div>
						</div>

					</div>
				</div>
				<div class="box box_02 tab_html">
					<div class="tab_nav">
						<ul>
							<li><a href="javascript:;" class="tab_head on"
								data-show="productViewDiv" id="productViewBtn">图片详情</a></li>
							<li><a href="javascript:;" class="tab_head"
								data-show="commentViewDiv" id="commentViewBtn">评价晒单</a></li>
							<li><a href="javascript:;" class="tab_head"
								data-show="attrsViewDiv" id="attrViewBtn">参数及问题</a></li>
						</ul>
					</div>
					<div class="tab_product tab_item" id="productViewDiv">
						<div class="product_main">${itemDesc['itemdesc']}</div>
					</div>
					<div class="tab_comment tab_item hide" id="commentViewDiv"></div>
					<div class="tab_attrs tab_item hide" id="attrsViewDiv">
						<ul class="product_attrs">
							<li><span class="tit">颜色</span><span class="val">白色</span></li>
						</ul>
						<div class="product_question">
							<div class="head">
								<span>产品提问</span>
							</div>
							<div class="que_list hide" id="questionList"></div>
						</div>
						<div class="product_question_send">
							<div class="text_inarea">
								<textarea id="question_textarea"
									placeholder="您的提问将会发给小米官方客服，购买过的用户也可以参与回答。"></textarea>
							</div>
							<div class="btns">
								<a href="javascript:;" class="btn j_question_submit"><span>提交问题</span></a>
							</div>
						</div>
					</div>

					<input type="hidden" name="goods_id" id="goods_id"
						value="2152800032">
					<div class="product_addCart">
						<div class="tip-success hide">
							<div class="inner">
								<div class="tip-txt">
									<span>商品已加入购物车</span>
								</div>
								<div class="btns">
									<a href="http://m.mi.com/index.html#ac=shopping&op=index"
										class="btn"><span>前往购物车</span></a>
								</div>
							</div>
						</div>
						<a action="AddShoppingCartBtn" id="bt_sp"
							class="button active_button" href="javascript:;">加入购物车</a>
					</div>
				</div>
				<div class="share_view hide">
					<div class="share_view_inner">
						<div class="share_view_lay">
							<h3>分享到</h3>
							<ul>
								<li><a href="javascript:;" class="WB" data-share="WB">新浪微博</a></li>
								<li><a href="javascript:;" class="TWB" data-share="TWB">腾讯微博</a></li>
								<li><a href="javascript:;" class="QZ" data-share="QZ">QQ空间</a></li>
							</ul>
							<div class="close"></div>
						</div>
					</div>
					<div class="mask"></div>
				</div>
			</div>
			<div id="productViewWeixinTip" class="weixin_share_tip"></div>
			<div id="footer" class="footer">
				<div class="tip">
					<a href="http://www.mi.com/?mobile" class="goDesktop"><span>切换到电脑版</span></a>
				</div>
				<div class="links">
					<a
						href="http://p.www.xiaomi.com/m/huodong/page/2013/0922/index.html"><span
						class="linksBtn">立即下载</span>
						<p>
							<strong>小米商城客户端</strong><span>与米粉交朋友</span>
						</p></a>
				</div>
			</div>
		</div>
	</div>
	<div id="maskLoad" class="maskLoad" style="display: none;">
		<span>正在加载...</span>
	</div>
	<div id="mask" class="mask hide"></div>
	<div class="pop_panel hide" id="pop_panel">
		<div class="content">
			<div class="bd giftCard_view">
				<div class="gfc5">
					<div class="b b1">
						<p>提示</p>
					</div>
					<div class="b b2">
						<p id="content"></p>
					</div>
				</div>
			</div>
		</div>
		<a href="javascript:;" id="CloseMaskBtn" class="close"
			data-stat-id="2c8d124be55949cc"
			onclick="_msq.push([&#39;trackEvent&#39;, &#39;f086bb7168acc7da-2c8d124be55949cc&#39;, &#39;javascript:;&#39;, &#39;pcpid&#39;]);"><img
			src="images/close.png"></a>
	</div>
	<div id="popup" style="display: none">
		<p></p>
	</div>
	<div class="loading_view" style="visibility: hidden">
		<div class="loading_wrap">
			<div class="loading_msg">正在加载 ...</div>
			<a href="javascript:;" class="close"
				onclick="_msq.push([&#39;trackEvent&#39;, &#39;f086bb7168acc7da-22892765a2389a00&#39;, &#39;javascript:;&#39;, &#39;pcpid&#39;]);MipuUtil.MaskLoading.close();"
				data-stat-id="22892765a2389a00"><img
				src="images/loadingClose.png"></a>
		</div>
	</div>

</body>
</html>