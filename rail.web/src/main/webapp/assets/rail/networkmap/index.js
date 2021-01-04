/**
 * Created by Administrator on 2017/1/13.
 */
(function ($) {
    $(function () {

        //创建点类图形
        var createPointShape = function (point) {
            var radios = 3;
            var pointShape = new createjs.Shape();
            pointShape.graphics.beginFill("#FFFFFF").drawCircle(point.x, point.y, radios).endFill();
            pointShape.cursor = "pointer";
            pointShape.originalX = point.x;
            pointShape.originalY = point.y;

            function handleInteraction(event) {
                var originalX = event.target.originalX;
                var originalY = event.target.originalY;
                var graphics = event.target.graphics;
                if (event.type == "mouseover") {
                    graphics.clear().beginFill("red").drawCircle(originalX, originalY, radios);
                } else {
                    graphics.clear().beginFill("#FFFFFF").drawCircle(originalX, originalY, radios);
                }
            }

            pointShape.on("mouseover", handleInteraction);
            pointShape.on("mouseout", handleInteraction);

            return pointShape;
        };

        //创建文本类图形
        var createTextShape = function (text) {
            var textShape = new createjs.Text(text.text, "10px Arial", "#FFFFFF");
            textShape.x = text.x;
            textShape.y = text.y;
            textShape.textBaseline = "middle";
            textShape.cursor = "pointer";

            var hitArea = new createjs.Shape();
            hitArea.graphics.beginFill("#000").drawRect(0, -textShape.getMeasuredHeight() / 2,
                textShape.getMeasuredWidth(), textShape.getMeasuredHeight());
            textShape.hitArea = hitArea;

            function handleInteraction(event) {
                event.target.color = (event.type == "mouseover") ? "red" : "#FFFFFF";
            }

            textShape.on("mouseover", handleInteraction);
            textShape.on("mouseout", handleInteraction);

            return textShape;
        };

        //创建线串类图形
        var createLineStringShape = function (lineString) {
            var lineStringShape = new createjs.Shape();
            var graphics = lineStringShape.graphics;
            var xys = lineString.xys;
            graphics.beginStroke("#FFFFFF").setStrokeStyle(1).moveTo(xys[0], xys[1]);
            for (var xysIndex = 2; xysIndex < xys.length; xysIndex += 2) {
                graphics.lineTo(xys[xysIndex], xys[xysIndex + 1]);
            }
            graphics.endStroke();
            lineStringShape.cursor = "pointer";
            lineStringShape.xys = xys;

            function handleInteraction(event) {
                if (event.type == "mouseover") {
                    var xys = event.target.xys;
                    var graphics = event.target.graphics;
                    graphics.clear().beginStroke("red").setStrokeStyle(3).moveTo(xys[0], xys[1]);
                    for (var xysIndex = 2; xysIndex < xys.length; xysIndex += 2) {
                        graphics.lineTo(xys[xysIndex], xys[xysIndex + 1]);
                    }
                    graphics.endStroke();
                    this.stage.update();
                } else {
                    var xys = event.target.xys;
                    var graphics = event.target.graphics;
                    graphics.clear().beginStroke("#FFFFFF").setStrokeStyle(1).moveTo(xys[0], xys[1]);
                    for (var xysIndex = 2; xysIndex < xys.length; xysIndex += 2) {
                        graphics.lineTo(xys[xysIndex], xys[xysIndex + 1]);
                    }
                    graphics.endStroke();
                    this.stage.update();
                }
            }

            lineStringShape.on("mouseover", handleInteraction);
            lineStringShape.on("mouseout", handleInteraction);

            return lineStringShape;
        };

        var doLoad = function (view) {
            var url = jQuery.dynamicServer + '/rail/networkmap/features.htm?';
            url += 'zoom-level=' + view.zoomLevel;
            url += '&previous-zoom-level=' + view.previousZoomLevel;
            url += '&view-width=' + view.width;
            url += '&view-height=' + view.height;
            url += '&figure-center-view-coordinate-delta-x=' + view.figureCenterViewCoordinateDeltaX;
            url += '&figure-center-view-coordinate-delta-y=' + view.figureCenterViewCoordinateDeltaY;
            url += '&locked-view-coordinate-delta-x=' + view.lockedViewCoordinateDeltaX;
            url += '&locked-view-coordinate-delta-y=' + view.lockedViewCoordinateDeltaY;

            $.ajax({
                type: 'GET',
                url: url,
                dataType: 'json',
                success: function (result) {
                    if (result["success"]) {
                        //获取缩放等级
                        view.zoomLevel = result.data.zoomLevel;
                        //最小缩放等级
                        view.minZoomLevel = result.data.minZoomLevel;
                        //最大缩放等级
                        view.maxZoomLevel = result.data.maxZoomLevel;
                        //获取视同中心点视图坐标偏移量x
                        view.figureCenterViewCoordinateDeltaX = result.data.figureCenterViewCoordinateDeltaX;
                        //获取视同中心点视图坐标偏移量y
                        view.figureCenterViewCoordinateDeltaY = result.data.figureCenterViewCoordinateDeltaY;

                        view.removeAllShapes();

                        for (var index = 0; index < result.data.points.length; index++) {
                            var point = result.data.points[index];
                            view.addShape(createPointShape(point));
                        }
                        for (var index = 0; index < result.data.texts.length; index++) {
                            var text = result.data.texts[index];
                            view.addShape(createTextShape(text));
                        }
                        for (var index = 0; index < result.data.lineStrings.length; index++) {
                            var lineString = result.data.lineStrings[index];
                            view.addShape(createLineStringShape(lineString));
                        }

                        view.loading = false;
                    }
                }
            });
        };

        //初始化canvas尺寸
        //必须通过这个方法设置canvas画板尺寸，其他方式只是设置css显示尺寸

        var vectorTileCanvas = $("#vectorTileCanvas");

        var actions = $("#actions");
        vectorTileCanvas.get(0).width = vectorTileCanvas.parent().width();
        vectorTileCanvas.get(0).height = vectorTileCanvas.parent().height();

        var vectorTile = new VectorTile();
        vectorTile.canvasId = "vectorTileCanvas";
        vectorTile.canvasWidth = vectorTileCanvas.width();
        vectorTile.canvasHeight = vectorTileCanvas.height();
        vectorTile.initialize();

        $("#vectorTileCanvas").on("mousedown", function (e) {
            vectorTile.actionHandler.mouseDown(e.originalEvent);
        });
        $("#vectorTileCanvas").on("mousemove", function (e) {
            vectorTile.actionHandler.mouseMove(e.originalEvent);
        });
        $("#vectorTileCanvas").on("mouseup", function (e) {
            vectorTile.actionHandler.mouseUp(e.originalEvent);
        });
        $("#vectorTileCanvas").on("mousewheel", function (e) {
            vectorTile.actionHandler.mouseWheel(e.originalEvent);
        });

        $("#zoomIn").on("click", function (e) {
            vectorTile.actionHandler.zoomIn(e);
        });
        $("#zoomOut").on("click", function (e) {
            vectorTile.actionHandler.zoomOut(e);
        });
        $("#moveUp").on("click", function (e) {
            vectorTile.actionHandler.moveUp(e);
        });
        $("#moveDown").on("click", function (e) {
            vectorTile.actionHandler.moveDown(e);
        });
        $("#moveLeft").on("click", function (e) {
            vectorTile.actionHandler.moveLeft(e);
        });
        $("#moveRight").on("click", function (e) {
            vectorTile.actionHandler.moveRight(e);
        });
        $("#moveCenter").on("click", function (e) {
            vectorTile.actionHandler.moveCenter(e);
        });
        $("#load").on("click", function (e) {
            vectorTile.actionHandler.moveCenter(e);
        });

        vectorTile.view.doLoad = doLoad;
        vectorTile.view.zoomLevel = 1;

        vectorTile.startUp();
    });
})(jQuery);