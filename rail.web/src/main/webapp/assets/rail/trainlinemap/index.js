/**
 * Created by Administrator on 2017/1/13.
 */
(function ($) {
    $(function () {

            //创建点类图形
            var createPointShape = function (point) {
                var radios = 3;
                var pointShape = new createjs.Shape();
                pointShape.graphics.beginFill("#000000").drawCircle(point.x, point.y, radios).endFill();
                pointShape.cursor = "pointer";
                pointShape.orignalX = point.x;
                pointShape.orignalY = point.y;

                function handleInteraction(event) {
                    var originalX = event.target.orignalX;
                    var originalY = event.target.orignalY;
                    var graphics = event.target.graphics;
                    if (event.type == "mouseover") {
                        graphics.clear().beginFill("red").drawCircle(originalX, originalY, radios);
                    } else {
                        graphics.clear().beginFill("#000000").drawCircle(originalX, originalY, radios);
                    }
                }

                pointShape.on("mouseover", handleInteraction);
                pointShape.on("mouseout", handleInteraction);

                return pointShape;
            };

            //创建文本类图形
            var createTextShape = function (text) {
                var textShape = new createjs.Text(text.text, "10px Arial", "#000000");
                textShape.x = text.x;
                textShape.y = text.y;
                textShape.textBaseline = "middle";
                textShape.cursor = "pointer";

                var hitArea = new createjs.Shape();
                hitArea.graphics.beginFill("#000000").drawRect(0, -textShape.getMeasuredHeight() / 2,
                    textShape.getMeasuredWidth(), textShape.getMeasuredHeight());
                textShape.hitArea = hitArea;

                function handleInteraction(event) {
                    event.target.color = (event.type == "mouseover") ? "red" : "#000000";
                }

                textShape.on("mouseover", handleInteraction);
                textShape.on("mouseout", handleInteraction);

                return textShape;
            };

            //创建时间轴轴线线串类图形
            var createTimeAxisLineStringShape = function (lineString) {
                var lineStringShape = new createjs.Shape();
                var graphics = lineStringShape.graphics;
                var xys = lineString.xys;
                graphics.beginStroke("#2dd92d").setStrokeStyle(1).moveTo(xys[0], xys[1]);
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
                        graphics.clear().beginStroke("#2dd92d").setStrokeStyle(1).moveTo(xys[0], xys[1]);
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

            //创建时间轴轴线线串类图形
            var createTimeAxisMinuteLineStringShape = function (lineString) {
                var lineStringShape = new createjs.Shape();
                var graphics = lineStringShape.graphics;
                var xys = lineString.xys;
                graphics.beginStroke("#2dd92d").setStrokeStyle(1).moveTo(xys[0], xys[1]);
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
                        graphics.clear().beginStroke("#2dd92d").setStrokeStyle(1).moveTo(xys[0], xys[1]);
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

            //创建时间轴轴线线串类图形
            var createTimeAxis10MinuteLineStringShape = function (lineString) {
                var lineStringShape = new createjs.Shape();
                var graphics = lineStringShape.graphics;
                var xys = lineString.xys;
                graphics.beginStroke("#2dd92d").setStrokeStyle(1).moveTo(xys[0], xys[1]);
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
                        graphics.clear().beginStroke("#2dd92d").setStrokeStyle(1).moveTo(xys[0], xys[1]);
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

            //创建时间轴轴线线串类图形
            var createTimeAxis30MinuteLineStringShape = function (lineString) {
                var lineStringShape = new createjs.Shape();
                var graphics = lineStringShape.graphics;
                var xys = lineString.xys;
                graphics.beginStroke("#1d7f1d").setStrokeStyle(1).moveTo(xys[0], xys[1]);
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
                        graphics.clear().beginStroke("#1d7f1d").setStrokeStyle(1).moveTo(xys[0], xys[1]);
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

            //创建时间轴轴线线串类图形
            var createTimeAxis60MinuteLineStringShape = function (lineString) {
                var lineStringShape = new createjs.Shape();
                var graphics = lineStringShape.graphics;
                var xys = lineString.xys;
                graphics.beginStroke("#2dd92d").setStrokeStyle(2).moveTo(xys[0], xys[1]);
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
                        graphics.clear().beginStroke("#2dd92d").setStrokeStyle(2).moveTo(xys[0], xys[1]);
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

            //创建文本类图形
            var createTimeAxis60MinuteTextShape = function (text) {
                var textShape = new createjs.Text(text.text, "10px Arial", "#000000");
                textShape.textAlign = "center";
                textShape.x = text.x;
                textShape.y = text.y;
                textShape.textBaseline = "middle";
                textShape.cursor = "pointer";

                var hitArea = new createjs.Shape();
                hitArea.graphics.beginFill("#000000").drawRect(0, -textShape.getMeasuredHeight() / 2,
                    textShape.getMeasuredWidth(), textShape.getMeasuredHeight());
                textShape.hitArea = hitArea;

                function handleInteraction(event) {
                    event.target.color = (event.type == "mouseover") ? "red" : "#000000";
                }

                textShape.on("mouseover", handleInteraction);
                textShape.on("mouseout", handleInteraction);

                return textShape;
            };


            //创建文本类图形
            var createTimeAxisDayTextShape = function (text) {
                var textShape = new createjs.Text(text.text, "10px Arial", "#000000");
                textShape.textAlign = "left";
                textShape.x = text.x;
                textShape.y = text.y;
                textShape.textBaseline = "middle";
                textShape.cursor = "pointer";

                var hitArea = new createjs.Shape();
                hitArea.graphics.beginFill("#000000").drawRect(0, -textShape.getMeasuredHeight() / 2,
                    textShape.getMeasuredWidth(), textShape.getMeasuredHeight());
                textShape.hitArea = hitArea;

                function handleInteraction(event) {
                    event.target.color = (event.type == "mouseover") ? "red" : "#000000";
                }

                textShape.on("mouseover", handleInteraction);
                textShape.on("mouseout", handleInteraction);

                return textShape;
            };

            //创建时间轴轴线线串类图形
            var createTableTimeAxisLineStringShape = function (lineString) {
                var lineStringShape = new createjs.Shape();
                var graphics = lineStringShape.graphics;
                var xys = lineString.xys;
                graphics.beginStroke("#2dd92d").setStrokeStyle(1).moveTo(xys[0], xys[1]);
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
                        graphics.clear().beginStroke("#2dd92d").setStrokeStyle(1).moveTo(xys[0], xys[1]);
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

            //创建左表头文本类图形
            var createTableLeftHeaderTextShape = function (text) {
                var textShape = new createjs.Text(text.text, "10px Arial", "#000000");
                textShape.textAlign = "center";
                textShape.x = text.x;
                textShape.y = text.y;
                textShape.textBaseline = "middle";
                textShape.cursor = "pointer";

                var hitArea = new createjs.Shape();
                hitArea.graphics.beginFill("#000000").drawRect(0, -textShape.getMeasuredHeight() / 2,
                    textShape.getMeasuredWidth(), textShape.getMeasuredHeight());
                textShape.hitArea = hitArea;

                function handleInteraction(event) {
                    event.target.color = (event.type == "mouseover") ? "red" : "#000000";
                }

                textShape.on("mouseover", handleInteraction);
                textShape.on("mouseout", handleInteraction);

                return textShape;
            };

            //创建运行线线串类图形
            var createTrainlineLineStringShape = function (lineString) {
                var lineStringShape = new createjs.Shape();
                var graphics = lineStringShape.graphics;
                var xys = lineString.xys;
                graphics.beginStroke("black").setStrokeStyle(1).moveTo(xys[0], xys[1]);
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
                        graphics.clear().beginStroke("black").setStrokeStyle(1).moveTo(xys[0], xys[1]);
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
                var url = jQuery.dynamicServer + '/rail/trainlinemap/features.htm?';
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

                            /////////////////////////////////////////////////////////////////////////////
                            for (var index = 0; index < result.data.timeAxisLineStrings.length; index++) {
                                var lineString = result.data.timeAxisLineStrings[index];
                                view.addShape(createTimeAxisLineStringShape(lineString));
                            }
                            for (var index = 0; index < result.data.timeAxisMinuteLineStrings.length; index++) {
                                var lineString = result.data.timeAxisMinuteLineStrings[index];
                                view.addShape(createTimeAxisMinuteLineStringShape(lineString));
                            }
                            for (var index = 0; index < result.data.timeAxis10MinuteLineStrings.length; index++) {
                                var lineString = result.data.timeAxis10MinuteLineStrings[index];
                                view.addShape(createTimeAxis10MinuteLineStringShape(lineString));
                            }
                            for (var index = 0; index < result.data.timeAxis30MinuteLineStrings.length; index++) {
                                var lineString = result.data.timeAxis30MinuteLineStrings[index];
                                view.addShape(createTimeAxis30MinuteLineStringShape(lineString));
                            }
                            for (var index = 0; index < result.data.timeAxis60MinuteLineStrings.length; index++) {
                                var lineString = result.data.timeAxis60MinuteLineStrings[index];
                                view.addShape(createTimeAxis60MinuteLineStringShape(lineString));
                            }
                            for (var index = 0; index < result.data.timeAxis60MinuteTexts.length; index++) {
                                var text = result.data.timeAxis60MinuteTexts[index];
                                view.addShape(createTimeAxis60MinuteTextShape(text));
                            }
                            for (var index = 0; index < result.data.timeAxisDayTexts.length; index++) {
                                var text = result.data.timeAxisDayTexts[index];
                                view.addShape(createTimeAxisDayTextShape(text));
                            }
                            for (var index = 0; index < result.data.tableTimeAxisLineStrings.length; index++) {
                                var lineString = result.data.tableTimeAxisLineStrings[index];
                                view.addShape(createTableTimeAxisLineStringShape(lineString));
                            }
                            for (var index = 0; index < result.data.tableLeftHeaderTexts.length; index++) {
                                var text = result.data.tableLeftHeaderTexts[index];
                                view.addShape(createTableLeftHeaderTextShape(text));
                            }

                            for (var index = 0; index < result.data.trainlines.length; index++) {
                                var trainline = result.data.trainlines[index];
                                for (var lineStringIndex = 0; lineStringIndex < trainline.lineStrings.length; lineStringIndex++) {
                                    var lineString = trainline.lineStrings[lineStringIndex];
                                    view.addShape(createTrainlineLineStringShape(lineString));
                                }
                            }

                            view.loading = false;
                        }
                    }
                });
            };

            //初始化canvas尺寸
            //必须通过这个方法设置canvas画板尺寸，其他方式只是设置css显示尺寸
            var canvas = $("#canvas");
            canvas.width = canvas.parent().width();
            canvas.height = canvas.parent().height();


            var vectorTile = new VectorTile();
            vectorTile.canvasId = "canvas";
            vectorTile.canvasWidth = canvas.width;
            vectorTile.canvasHeight = canvas.height;
            vectorTile.initialize();

            canvas.on("mousedown", function (e) {
                vectorTile.actionHandler.mouseDown(e);
            });
            canvas.on("mousemove", function (e) {
                vectorTile.actionHandler.mouseMove(e);
            });
            canvas.on("mouseup", function (e) {
                vectorTile.actionHandler.mouseUp(e);
            });
            canvas.on("mousewheel", function (e) {
                vectorTile.actionHandler.mouseWheel(e);
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
        }
    );
})(jQuery);