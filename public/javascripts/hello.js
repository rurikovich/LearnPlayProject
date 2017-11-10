if (window.console) {
    console.log("Welcome to your Play application's JavaScript!");
}


$(document).ready(function() {


    $.ajax({
        url: '/getFigures',
        success: function(data) {
            var width = window.innerWidth;
            var height = window.innerHeight;

            var stage = new Konva.Stage({
                container: 'container',
                width: width,
                height: height
            });

            var layer = new Konva.Layer();
            console.log(data);
            data.forEach(function(item, i, arr) {
                console.log(item);
                console.log(item.points);
                var poly = new Konva.Line({
                    points: item.points,
                    fill: '#00D2FF',
                    stroke: 'black',
                    strokeWidth: 5,
                    closed : true
                });

                // add the shape to the layer
                layer.add(poly);

            });


            // add the layer to the stage
            stage.add(layer);


        }
    });





});
