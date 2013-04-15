<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8" />
<title>Dashboard I Admin Panel</title>

<link rel="stylesheet"
	href="${resource(dir: 'geolocation/css', file: 'layout.css')}"
	type="text/css">

<script
	src="${resource(dir: 'geolocation/js', file: 'jquery-1.5.2.min.js')}"
	type="text/javascript"></script>
<script src="${resource(dir: 'geolocation/js', file: 'hideshow.js')}"
	type="text/javascript"></script>
<script
	src="${resource(dir: 'geolocation/js', file: 'jquery.tablesorter.min.js')}"
	type="text/javascript"></script>
<script type="text/javascript"
	src="${resource(dir: 'geolocation/js', file: 'jquery.equalHeight.js')}"></script>
<script type="text/javascript"
	src="http://maps.google.com/maps/api/js?sensor=false"></script>
<style type="text/css">
.map_holder {
	float: none;
	width: 100%;
	height: 445px;
	box-shadow: 1px 1px 4px 2px rgba(0, 0, 0, 0.7);
}
</style>
<script type="text/javascript">
	$(document).ready(function() 
    	{ 
      	  $(".tablesorter").tablesorter(); 
   	 } 
	);
	$(document).ready(function() {

	//When page loads...
	$(".tab_content").hide(); //Hide all content
	$("ul.tabs li:first").addClass("active").show(); //Activate first tab
	$(".tab_content:first").show(); //Show first tab content

	//On Click Event
	$("ul.tabs li").click(function() {

		$("ul.tabs li").removeClass("active"); //Remove any "active" class
		$(this).addClass("active"); //Add "active" class to selected tab
		$(".tab_content").hide(); //Hide all tab content

		var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content
		$(activeTab).fadeIn(); //Fade in the active ID content
		return false;
	});

});
    </script>
<script type="text/javascript">
    $(function(){
        $('.column').equalHeight();
    });
</script>

</head>


<body>	
	<header id="header">
		<hgroup>
			<h1 class="site_title">
				<a href="index.html">Sistema - SMRT</a>
			</h1>
			<h2 class="section_title">EZF305 - Peugeot 305 HDI 5 ptas - 2005
				- Rojo Lucifer</h2>
			<div class="btn_view_site">
				<a href="http://www.medialoot.com">Sitio Web</a>
			</div>
		</hgroup>
	</header>
	<!-- end of header bar -->

	<section id="secondary_bar">
		<div class="user">
			<p>
				David Portaluppi (<a href="#">3 Mensajes</a>)
			</p>
			<!-- <a class="logout_user" href="#" title="Logout">Logout</a> -->
		</div>
		<div class="breadcrumbs_container">
			<article class="breadcrumbs">
				<a href="index.html">Sistema - SMRT</a>
				<div class="breadcrumb_divider"></div>
				<a class="current">EZF305 - Peugeot 305</a>
			</article>
		</div>
	</section>
	<!-- end of secondary bar -->

	<aside id="sidebar" class="column">
		<form class="quick_search">
			<input type="text" value="Buscar Vehiculo"
				onfocus="if(!this._haschanged){this.value=''};this._haschanged=true;">
		</form>
		<hr />
		<h3>Vehiculos</h3>
		<ul class="toggle">
			<li class="icn_tags"><a href="#">EZF305</a></li>
			<li class="icn_tags"><a href="#">SGT573</a></li>
			<li class="icn_tags"><a href="#">CMM098</a></li>
			<li class="icn_tags"><a href="#">MHQ959</a></li>
		</ul>
		<h3>Usuarios</h3>
		<ul class="toggle">
			<li class="icn_add_user"><a href="#">Nuevo</a></li>
			<li class="icn_view_users"><a href="#">Lista</a></li>
			<li class="icn_profile"><a href="#">Tu Perfil</a></li>
		</ul>
		<!--
		<h3>Vehiculos</h3>
		<ul class="toggle">
			<li class="icn_add_user"><a href="#">Nuevo</a></li>
			<li class="icn_view_users"><a href="#">Lista</a></li>			
		</ul>
		-->
		<h3>Admin</h3>
		<ul class="toggle">
			<li class="icn_settings"><a href="#">Options</a></li>
			<li class="icn_security"><a href="#">Security</a></li>
			<li class="icn_jump_back"><a href="../login/login.html">Logout</a></li>
		</ul>

		<footer>
			<hr />
			<p>
				<strong>Copyright &copy; 2011 Sistema - SMRT</strong>
			</p>
			<p>
				Theme by <a href="http://www.medialoot.com">MediaLoot</a>
			</p>
		</footer>
	</aside>
	<!-- end of sidebar -->

	<section id="main" class="column">
		<!--	
		<h4 class="alert_info">Vehiculo: EZF305 - Peugeot 305 HDI 5 ptas - 2005 - Rojo Lucifer</h4>
		<h4 class="alert_warning">A Warning Alert</h4>		
		-->
		<h4 class="alert_error">Velocidad Maxima superada - Vel. Max. 100
			K/H - Vel. 110 k/h</h4>

		<!--
		<h4 class="alert_success">Conexion Exitosa: EZF305 - Peugeot 305 HDI 5 ptas</h4>
		-->
		<article class="module width_full">
			<header>
				<h3>Recorrido</h3>
			</header>
			<div class="module_content">

				<div class="map_holder" id="map_canvas"></div>

				<div class="clear"></div>
			</div>
		</article>
		<!-- end maps -->

		<article class="module width_full">
			<header>
				<h3>Medidas</h3>
			</header>
			<div class="module_content">
				<article class="stats_graph">
					<img
						src="http://chart.apis.google.com/chart?chxr=0,0,3000&chxt=y&chs=520x140&cht=lc&chco=76A4FB,80C65A&chd=s:Tdjpsvyvttmiihgmnrst,OTbdcfhhggcTUTTUadfk&chls=2|2&chma=40,20,20,30"
						width="520" height="140" alt="" />
				</article>

				<article class="stats_overview">
					<div class="overview_today">
						<p class="overview_day">Vel. [K/H]</p>
						<p class="overview_count" id="ff1001">1,876</p>
						<p class="overview_type">ahora</p>
						<p class="overview_count" id="ff1001_max">2,103</p>
						<p class="overview_type">maximo</p>
					</div>
					<div class="overview_previous">
						<p class="overview_day">Acel. [g]</p>
						<p class="overview_count" id="ff1223">1,646</p>
						<p class="overview_type">ahora</p>
						<p class="overview_count" id="ff1223_max">2,054</p>
						<p class="overview_type">Maximo</p>
					</div>
				</article>
				<div class="clear"></div>
			</div>
		</article>
		<!-- end of stats article -->

		<article class="module width_3_quarter">
			<header>
				<h3 class="tabs_involved">Alertas</h3>
				<ul class="tabs">
					<li><a href="#tab1">Nuevas</a></li>
					<li><a href="#tab2">Todas</a></li>
				</ul>
			</header>

			<div class="tab_container">
				<div id="tab1" class="tab_content">
					<table class="tablesorter" cellspacing="0">
						<thead>
							<tr>
								<th></th>
								<th>Descripcion</th>
								<th>Vehiculo</th>
								<th>Fecha</th>
								<th>Acciones</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><input type="checkbox"></td>
								<td>Velocidad Maxima superada - Vel. Max. 100 K/H - Vel.
									110 k/h</td>
								<td>EZF305</td>
								<td>25 Marzo 2013 14:03</td>
								<td><input type="image"
									src="${resource(dir: 'geolocation/images', file: 'icn_edit.png')}"
									title="Edit"> <input type="image"
									src="${resource(dir: 'geolocation/images', file: 'icn_trash.png')}"
									title="Trash"></td>
							</tr>
							<tr>
								<td><input type="checkbox"></td>
								<td>Ipsum Lorem Dolor Sit Amet</td>
								<td>Freebies</td>
								<td>6th April 2011</td>
								<td><input type="image"
									src="${resource(dir: 'geolocation/images', file: 'icn_edit.png')}"
									title="Edit"><input type="image"
									src="${resource(dir: 'geolocation/images', file: 'icn_trash.png')}"
									title="Trash"></td>
							</tr>
							<tr>
								<td><input type="checkbox"></td>
								<td>Sit Amet Dolor Ipsum</td>
								<td>Tutorials</td>
								<td>10th April 2011</td>
								<td><input type="image"
									src="${resource(dir: 'geolocation/images', file: 'icn_edit.png')}"
									title="Edit"><input type="image"
									src="${resource(dir: 'geolocation/images', file: 'icn_trash.png')}"
									title="Trash"></td>
							</tr>
							<tr>
								<td><input type="checkbox"></td>
								<td>Dolor Lorem Amet</td>
								<td>Articles</td>
								<td>16th April 2011</td>
								<td><input type="image"
									src="${resource(dir: 'geolocation/images', file: 'icn_edit.png')}"
									title="Edit"><input type="image"
									src="${resource(dir: 'geolocation/images', file: 'icn_trash.png')}"
									title="Trash"></td>
							</tr>
							<tr>
								<td><input type="checkbox"></td>
								<td>Dolor Lorem Amet</td>
								<td>Articles</td>
								<td>16th April 2011</td>
								<td><input type="image"
									src="${resource(dir: 'geolocation/images', file: 'icn_edit.png')}"
									title="Edit"><input type="image"
									src="${resource(dir: 'geolocation/images', file: 'icn_trash.png')}"
									title="Trash"></td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- end of #tab1 -->

				<div id="tab2" class="tab_content">
					<table class="tablesorter" cellspacing="0">
						<thead>
							<tr>
								<th></th>
								<th>Comment</th>
								<th>Posted by</th>
								<th>Posted On</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><input type="checkbox"></td>
								<td>Lorem Ipsum Dolor Sit Amet</td>
								<td>Mark Corrigan</td>
								<td>5th April 2011</td>
								<td><input type="image"
									src="${resource(dir: 'geolocation/images', file: 'icn_edit.png')}"
									title="Edit"><input type="image"
									src="${resource(dir: 'geolocation/images', file: 'icn_trash.png')}"
									title="Trash"></td>
							</tr>
							<tr>
								<td><input type="checkbox"></td>
								<td>Ipsum Lorem Dolor Sit Amet</td>
								<td>Jeremy Usbourne</td>
								<td>6th April 2011</td>
								<td><input type="image"
									src="${resource(dir: 'geolocation/images', file: 'icn_edit.png')}"
									title="Edit"><input type="image"
									src="${resource(dir: 'geolocation/images', file: 'icn_trash.png')}"
									title="Trash"></td>
							</tr>
							<tr>
								<td><input type="checkbox"></td>
								<td>Sit Amet Dolor Ipsum</td>
								<td>Super Hans</td>
								<td>10th April 2011</td>
								<td><input type="image"
									src="${resource(dir: 'geolocation/images', file: 'icn_edit.png')}"
									title="Edit"><input type="image"
									src="${resource(dir: 'geolocation/images', file: 'icn_trash.png')}"
									title="Trash"></td>
							</tr>
							<tr>
								<td><input type="checkbox"></td>
								<td>Dolor Lorem Amet</td>
								<td>Alan Johnson</td>
								<td>16th April 2011</td>
								<td><input type="image"
									src="${resource(dir: 'geolocation/images', file: 'icn_edit.png')}"
									title="Edit"><input type="image"
									src="${resource(dir: 'geolocation/images', file: 'icn_trash.png')}"
									title="Trash"></td>
							</tr>
							<tr>
								<td><input type="checkbox"></td>
								<td>Dolor Lorem Amet</td>
								<td>Dobby</td>
								<td>16th April 2011</td>
								<td><input type="image"
									src="${resource(dir: 'geolocation/images', file: 'icn_edit.png')}"
									title="Edit"><input type="image"
									src="${resource(dir: 'geolocation/images', file: 'icn_trash.png')}"
									title="Trash"></td>
							</tr>
						</tbody>
					</table>

				</div>
				<!-- end of #tab2 -->

			</div>
			<!-- end of .tab_container -->

		</article>
		<!-- end of content manager article -->

		<article class="module width_quarter">
			<header>
				<h3>Mensajes</h3>
			</header>
			<div class="message_list">
				<div class="module_content">
					<div class="message">
						<p>Velocidad Maxima Superada. Valor: . Ubicacion: .</p>
						<p>
							<strong>EZF305</strong>
						</p>
					</div>
					<div class="message">
						<p>Vivamus sagittis lacus vel augue laoreet rutrum faucibus
							dolor.</p>
						<p>
							<strong>John Doe</strong>
						</p>
					</div>
					<div class="message">
						<p>Vivamus sagittis lacus vel augue laoreet rutrum faucibus
							dolor.</p>
						<p>
							<strong>John Doe</strong>
						</p>
					</div>
					<div class="message">
						<p>Vivamus sagittis lacus vel augue laoreet rutrum faucibus
							dolor.</p>
						<p>
							<strong>John Doe</strong>
						</p>
					</div>
					<div class="message">
						<p>Vivamus sagittis lacus vel augue laoreet rutrum faucibus
							dolor.</p>
						<p>
							<strong>John Doe</strong>
						</p>
					</div>
				</div>
			</div>
			<footer>
				<form class="post_message">
					<input type="text" value="Message"
						onfocus="if(!this._haschanged){this.value=''};this._haschanged=true;">
					<input type="submit" class="btn_post_message" value="" />
				</form>
			</footer>
		</article>
		<!-- end of messages article -->

		<div class="clear"></div>




	</section>
	<g:javascript></g:javascript>
	<script type="text/javascript">
	var autoSize;
	var myLatlng = new google.maps.LatLng(-32.95390243, -60.65740253);
	//function initialize() {
	var myOptions = {
		zoom: 14,
		center: myLatlng,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	}
	var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
	var flightPlanCoordinates = [];
	var flightPath = new google.maps.Polyline({
		path: flightPlanCoordinates,
		strokeColor: "#0000FF",
		strokeOpacity: 1.0,
		strokeWeight: 3
	});

	flightPath.setMap(map);
	// Set bounds
	var bounds = new google.maps.LatLngBounds ();
	for (var i = 0, LtLgLen = flightPlanCoordinates.length; i < LtLgLen; i++) {
		bounds.extend (flightPlanCoordinates[i]);
	}
	map.fitBounds(bounds);
	//}
	
	 
	function addCoor() {
		window.setInterval(update, 1000);
		return;		
	}
	
	function buildTrace(r) {
		var lon = r["EZF305"]["ff1005"].value;
		var lat = r["EZF305"]["ff1006"].value;		
		var vel = r["EZF305"]["ff1001"].value;
		var ace = r["EZF305"]["ff1223"].value;
		if(isNaN(lon)){return}
		vel = vel.toFixed(2);
		ace = ace.toFixed(2);
		$("#ff1001").text(vel);
		$("#ff1223").text(ace);
		
		var coor = new google.maps.LatLng(lat, lon);
		var image = 'images/beachflag.png';
		if(vel > 10 ) {
			
			flightPath.getPath().push(coor);
		}
		
		// Centrar mapa
		//map.setCenter(coor);
		
		//Definir limites del mapa
		if(autoSize != 0) {		
			var flightPlanCoordinates = flightPath.getPath();
			var bounds = new google.maps.LatLngBounds ();		
			var changeBound = function(coo, i){ 
				bounds.extend(coo);
			};		
			flightPlanCoordinates.forEach(changeBound);		
			map.fitBounds(bounds);
			autoSize = 0;
		}
		
		var image = "${resource(dir: 'geolocation/images', file: 'car.png')}"+'';
		var marker = new google.maps.Marker({
			position : coor,
			map : map,
			icon : image
		});
		
		try {oldmarker.setMap(null);} catch (e) {;}
        oldmarker = marker;
		
	}
	function update() {
		var input = {"EZF305":["ff1005","ff1006", "ff1001", "ff1223"]};
		
		$.ajax({
			url: 'values',
			type: 'POST',
			dataType: 'json',				
			data: "jsonQuery=" + JSON.stringify(input),
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			success: function (data) {
				
				// console.log(data);
				buildTrace(data);
			},
			error: function(a) {
				console.log(a);
				alert("error");		
			}
		});
	}
	
	function getHistory() {
		var minutes = 1000*60;
		var input = {"spanTime": 8*minutes, "upperTime": -1, "assets": {"EZF305":["ff1005","ff1006", "ff1001", "ff1223"]}};
		
		$.ajax({
			url: 'history',
			type: 'POST',
			dataType: 'json',				
			data: "jsonQuery=" + JSON.stringify(input),
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			success: function (data) {
				console.log(data);	
				if(data["EZF305"]["ff1005"] == undefined) {
					addCoor();
					return;
				}			
				// dibujo inicio
				var lonArray = data["EZF305"]["ff1005"]["data"];
				var latArray = data["EZF305"]["ff1006"]["data"];		
				/*
				var vel = data["EZF305"]["ff1001"]["data"];
				var ace = data["EZF305"]["ff1223"]["data"];
				*/
				flightPlanCoordinates = []
				for(var i in lonArray){
					var lon = lonArray[i].value;
					var lat = latArray[i].value;					
					var coor = new google.maps.LatLng(lat, lon);
					flightPlanCoordinates.push(coor)
				}																
				
				flightPath.setMap(null);
				flightPath = new google.maps.Polyline({
					path: flightPlanCoordinates,
					strokeColor: "#0000FF",
					strokeOpacity: 1.0,
					strokeWeight: 3
				});

				flightPath.setMap(map);
				
				// Set bounds
				var bounds = new google.maps.LatLngBounds ();
				for (var i = 0, LtLgLen = flightPlanCoordinates.length; i < LtLgLen; i++) {
					bounds.extend (flightPlanCoordinates[i]);
				}
				map.fitBounds(bounds);
				
				addCoor();
				// actualizo graficos				
			},
			error: function(a) {
				console.log(a);
				alert("error");		
			}
		});
	}
	
	$(function() {getHistory()});
</script>
</body>

</html>