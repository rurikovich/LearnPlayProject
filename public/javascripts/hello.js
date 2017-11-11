if (window.console) {
    console.log("Welcome to your Play application's JavaScript!");
}


$(document).ready(function () {
    var width = window.innerWidth;
    var height = window.innerHeight;

    var stage = new Konva.Stage({
        container: 'container',
        width: width,
        height: height
    });

    var layer = new Konva.Layer();
    // add the layer to the stage
    stage.add(layer);


    $.ajax({
        url: '/getFigures',
        success: function (data) {
            data.forEach(function (item, i, arr) {
                var poly = new Konva.Line({
                    points: item.points,
                    stroke: 'black',
                    strokeWidth: 5,
                    closed: true
                });
                layer.add(poly);
            });
            layer.draw();
        }
    });


    $.ajax({
        url: '/getLastRoute',
        success: function (data) {
            var route = new Konva.Line({
                points: data,
                stroke: 'green',
                strokeWidth: 2,
                lineJoin: 'round',
                dash: [33, 10]
            });
            layer.add(route);
            layer.draw();
        }
    });


});
