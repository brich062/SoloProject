<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!Doctype html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<!-- add css file below here -->
<link rel="stylesheet" href="<c:url value="/CSS/homenavbarfooterSTYLE.css"/>">
<!-- <script type="text/javascript" src="JS/dropdownMenu.js"/></script> -->


</head>
<body class="container hnf">
	
	<div class="container">
		<div class="slogan">
			<img src="<c:url value="/Resources/Images/Vulk.png"/>"/>
			<h1 class="main">Conquer!</h1>
		</div>
			<div>
				<div class="navbar">
  					<a href="/">Home</a>
  					<div class="dropdown">
  						<button class="dropbtn" onclick="myFunction()">Menu
    						<i class="fa fa-caret-down"></i>
  						</button>
  						<div class="dropdown-content" id="myDropdown">
  							<c:choose>
    							<c:when test="${user != null}">
      								<a class="dropdown-item" href="/profile/${user.id}">My Profile</a>
      								<a class="dropdown-item" href="/logout">Log Out</a>
      							</c:when>
      							<c:otherwise>
      								<a class="dropdown-item" href="/loginregister">Login/Register</a>
      							</c:otherwise>
      						</c:choose>
      						<a href="/products">Products</a>
      						<a href="/about">About Us</a>
      						<a href="/contact">Contact Us</a>
      				 	</div>
  					</div> 
  					<div>  
  				<ul class="nav navbar-nav navbar-right">
       				<li class="dropdown">
          			<a href="/shoppingcart" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"> <span class="fa fa-gift bigicon"></span><%-- <c:out items="${cart.quantity}"/> --%>- Items<span class="caret"></span></a>
          			<ul class="dropdown-menu dropdown-cart" role="menu">
              			<li>
                  			<span class="item">
                    			<span class="item-left">
                        			<img src="http://www.prepbootstrap.com/Content/images/template/menucartdropdown/item_1.jpg" alt="" />
                        			<span class="item-info">
                        	    		<span><%-- <c:out item="${cart.product.name}"/> --%></span>
                            			<span><%-- <c:out item="${cart.product.price}"/> --%></span>
                        			</span>
                    			</span>
                    			<span class="item-right">
                        			<button class="btn btn-danger  fa fa-close"></button>
                    			</span>
                			</span>
              			</li>
              		</ul>
              		</li>
              </ul>
           </div>
				</div>
  			</div>
  			
	</div>
	<div class="jBody">
	
	<jsp:doBody/>
	
	</div>
	<div class="footer" class="container">
		<div class="container" id="content">
			<a class="footItem" href="/about">About Us</a>
			<a class="footItem" href="https://www.instagram.com/bronson_the_odinson/"><img src="<c:url value="/WEB-INF/Resources/Images/instaLogo"/>"/></a>
		</div>
	</div>
<script type="text/javascript">
/* When the user clicks on the button, 
toggle between hiding and showing the dropdown content */
function myFunction() {
  document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown if the user clicks outside of it
window.onclick = function(e) {
  if (!e.target.matches('.dropbtn')) {
  var myDropdown = document.getElementById("myDropdown");
    if (myDropdown.classList.contains('show')) {
      myDropdown.classList.remove('show');
    }
  }
}
</script>
</body>

</html>