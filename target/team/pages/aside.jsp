<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<aside class="main-sidebar">


	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">


		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="${pageContext.request.contextPath}/img/soccer2.jpg"
					class="img-circle" alt="用户头像">
			</div>
			<div class="pull-left info">
				<p><security:authentication property="principal.username"></security:authentication></p>
				<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
			</div>
		</div>








		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header">菜单</li>
			<li id="admin-index"><a
				href="${pageContext.request.contextPath}/pages/main.jsp"><i
					class="fa fa-dashboard"></i> <span>首页</span></a></li>




			<li class="treeview"><a href="#"> <i class="fa fa-cogs"></i>
					<span>系统管理</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>

				<ul class="treeview-menu">
					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/user/findAll?page=1&size=5"> <i
							class="fa fa-circle-o"></i> 用户管理
					</a></li>
					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/role/findAll?page=1&size=5"> <i
							class="fa fa-circle-o"></i> 角色管理
					</a></li>
					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/permission/findAll?page=1&size=5">
							<i class="fa fa-circle-o"></i> 资源权限管理
					</a></li>
					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/log/findAll?page=1&size=50"> <i
							class="fa fa-circle-o"></i> 访问日志
					</a></li>
				</ul></li>




			<li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
					<span>基础数据</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>

				<ul class="treeview-menu">

					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/team/findAll?page=1&size=5">
							<i class="fa fa-circle-o"></i>球队管理
					</a></li>
					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/player/findAll?page=1&size=5"><i
							class="fa fa-circle-o"></i>球员管理
					</a></li>
					<li id="system-setting"><a
							href="${pageContext.request.contextPath}/coach/findAll?page=1&size=5"><i
							class="fa fa-circle-o"></i>教练管理
					</a></li>
					<li id="system-setting"><a
							href="${pageContext.request.contextPath}/doctor/findAll?page=1&size=5"><i
							class="fa fa-circle-o"></i>队医管理
					</a></li>
					<li id="system-setting"><a
							href="${pageContext.request.contextPath}/ground/findAll?page=1&size=5"><i
							class="fa fa-circle-o"></i>球场管理
					</a></li>
				</ul></li>



			<li class="treeview"><a href="#"> <i class="fa fa-soccer-ball-o"></i>
				<span>赛事信息</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>

				<ul class="treeview-menu">

					<li id="system-setting"><a
							href="${pageContext.request.contextPath}/battle/findAll?page=1&size=20">
						<i class="fa fa-circle-o"></i>比赛信息
					</a></li>


					<li id="system-setting"><a
							href="${pageContext.request.contextPath}/history/findAll?page=1&size=20"><i
							class="fa fa-circle-o"></i>历史战绩
					</a></li>

					<li id="system-setting"><a
							href="${pageContext.request.contextPath}/grade/findAll"><i
							class="fa fa-circle-o"></i>球队积分榜
					</a></li>

				</ul>
			</li>



		</ul>













	</section>
	<!-- /.sidebar -->




</aside>