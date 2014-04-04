<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
</head>
<body>
<iframe
        id = "maps"
        width="600"
        height="450"
        frameborder="0" style="border:0"
        src="https://www.google.com/maps/embed/v1/search?key=AIzaSyAY1KUoh2rYogfCZFm_CF78nqk2fKk7oA0&q=store&center=30.338471,-97.703958&zoom=16">
</iframe>
<script>
    setInterval(function(){
        $.get( "lastLocation", function( data ) {
            $("#maps").attr("src", data.url);
        });
    }, 5000)
</script>
</body>
</html>