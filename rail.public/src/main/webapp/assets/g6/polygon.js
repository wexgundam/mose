/**
 * Created by Administrator on 2020/6/1.
 */
(function loadPolygon() {
    // 注册多边形
    G6.registerNode(
        'polygon',
        {
            itemType: "node",
            draw(cfg, group) {
                const attrs = {...cfg.style, ...{points: cfg.points}};
                const keyShape = group.addShape('polygon', {
                    attrs: attrs,
                });

                return keyShape;
            },
            /**
             * 更新节点，包含文本
             * @override
             * @param  {Object} cfg 节点的配置项
             * @param  {Node} node 节点
             */
            update(cfg, node) {
                const group = node.getContainer(); // 获取容器
                const keyShape = group.getChildByIndex(0); // 按照添加的顺序
                const keyShapeAttrs = {...cfg.style, ...{points: cfg.points}};
                keyShape.attr(keyShapeAttrs); // 更新属性
            },
        },
        'single-node',
    );

    // 注册多边形的交互
    G6.registerBehavior('edit-polygon', {
        // 设定该自定义行为需要监听的事件及其响应函数
        getEvents() {
            // 监听的事件为 canvas:click，响应函数是 onClick
            return {
                'canvas:click': 'onClickCanvas',
                'node:mouseenter': 'onMouseenterNode',
                'node:mouseleave': 'onMouseleaveNode',
                'node:click': 'onClickNode',
                'node:dblclick': 'onDblClickNode',
                'node:dragstart': 'onDragstartNode',
                'node:drag': 'onDragNode',
                'node:dragend': 'onDragendNode',
            };
        },
        getDefaultCfg() {
            return {
                polygon: null, // 当前被编辑的polygon
                operations: [],// 操作点集合
                operationEdges: [],// 操作辅助线
            }
        },
        //创建操作点id
        createOperationId(polygonId, index, isVertex) {
            return 'polygon[' + polygonId + '][' + index + '][' + (isVertex ? 'T]' : 'F]');
        },
        //创建多边形边操作点集合
        createOperations(polygon) {
            const createModel = function (operationId, index, isVertex, x, y) {
                return {
                    id: operationId,
                    x: x,
                    y: y,
                    size: 10,
                    style: {
                        fill: isVertex ? 'lightsteelblue' : 'green',
                    },
                    // 节点在各状态下的样式
                    stateStyles: {
                        // hover 状态为 true 时的样式
                        hover: {
                            fillOpacity: 0.5,
                        },
                        // click 状态为 true 时的样式
                        click: {
                            lineWidth: 3,
                        },
                    },
                    index: index,
                    isVertex: isVertex,
                };
            };

            let x = polygon.getModel().x;
            let y = polygon.getModel().y;
            let points = polygon.getModel().points;
            const operations = [];
            // 因为模型采用的是回归原点方式，所以（坐标数组-2) / 2 == 多边形顶点数 == 多边形边数
            for (var index = 0; index < points.length; index++) {
                // 创建多边形顶点图形
                var operationId = this.createOperationId(polygon.getModel().id, index, true);
                var operation = graph.addItem('node',
                    createModel(operationId, index, true, x + points[index][0], y + points[index][1]));
                operations.push(operation);
                // 创建多边形边的中点图形
                var operationId = this.createOperationId(polygon.getModel().id, index, false);
                const nextPointIndex = (index + 1) % points.length;
                var operation = graph.addItem('node',
                    createModel(operationId, index, false,
                        x + (points[nextPointIndex][0] + points[index][0]) / 2,
                        y + (points[nextPointIndex][1] + points[index][1]) / 2));
                operations.push(operation);
            }
            return operations;
        },
        // 鼠标进入节点
        onMouseenterNode(evt) {
            if (evt.item.getModel().type != 'polygon'
                && this.operations.indexOf(evt.item) == -1) {
                return;
            }

            const polygon = evt.item; // 获取鼠标进入的节点元素对象
            polygon.setState('hover', !polygon.hasState('click')); // 设置当前节点的 hover 状态为 true
        },
        // 鼠标离开节点
        onMouseleaveNode(evt) {
            if (evt.item.getModel().type != 'polygon'
                && this.operations.indexOf(evt.item) == -1) {
                return;
            }

            const polygon = evt.item; // 获取鼠标离开的节点元素对象
            polygon.clearStates('hover');
        },
        // 点击canvas事件
        onClickCanvas(evt) {
            //清理正在编辑的polygon的操作点
            if (this.polygon != null) {
                this.operations.forEach(operation => {
                    this.graph.removeItem(operation);
                });
                this.operationEdges.forEach(edge => {
                    this.graph.removeItem(edge);
                });
                this.polygon.clearStates('click');
                this.polygon = null;
                this.operations.length = 0;
                this.operationEdges.length = 0;
            }
        },
        // 点击node事件
        onClickNode(evt) {
            if (evt.item.getModel().type != 'polygon') {
                return;
            }

            //清理正在编辑的polygon的操作点
            if (this.polygon != null) {
                this.operations.forEach(operation => {
                    this.graph.removeItem(operation);
                });
                this.polygon.clearStates('click');
            }
            //重复点击同一polygon，关闭概该polygon编辑状态
            if (this.polygon == evt.item) {
                this.polygon.setState('hover', true);
                this.polygon = null;
                this.operations.length = 0;
                this.operationEdges.length = 0;
                return;
            }

            const polygon = evt.item;
            this.polygon = polygon;
            this.polygon.clearStates('hover');
            this.polygon.setState('click', true);
            this.operations = this.createOperations(polygon);
        },
        //鼠标双击清除被双击的vertex类型的操作点
        onDblClickNode(evt) {
            if (this.operations.indexOf(evt.item) == -1
                || !evt.item.getModel().isVertex
                || this.polygon.getModel().points.length < 4) {
                return;
            }

            const polygon = this.polygon;
            // 获取操作点
            const operation = evt.item;

            polygon.getModel().points.splice(operation.getModel().index, 1);
            polygon.refresh();

            this.operationEdges.forEach(edge => {
                this.graph.removeItem(edge);
            });
            this.operations.forEach(operation => {
                this.graph.removeItem(operation);
            });
            this.operations = this.createOperations(polygon);
        },
        onDragstartNode(evt) {
            if (this.operations.indexOf(evt.item) == -1) {
                return;
            }

            const polygon = this.polygon;
            // 获取操作点
            const operation = evt.item;
            let points = polygon.getModel().points;
            let index = operation.getModel().index;
            const previousPointIndex = operation.getModel().isVertex ? (Math.abs(
                index + points.length - 1) % points.length) : (index);
            const nextPointIndex = (index + 1) % points.length;
            var edge = this.graph.addItem('edge', {
                source: operation.getModel().id,
                target: this.createOperationId(this.polygon.getModel().id, previousPointIndex, true),
                style: {
                    stroke: 'blue',
                    lineWidth: 1,
                },
            });
            this.operationEdges.push(edge);
            var edge = this.graph.addItem('edge', {
                source: operation.getModel().id,
                target: this.createOperationId(this.polygon.getModel().id, nextPointIndex, true),
                style: {
                    stroke: 'blue',
                    lineWidth: 1,
                },
            });
            this.operationEdges.push(edge);
        },
        // 鼠标拖拽多边形操作点
        onDragNode(evt) {
            if (this.operations.indexOf(evt.item) == -1) {
                return;
            }
            // 获取操作点
            const operation = evt.item;
            operation.updatePosition({x: evt.x, y: evt.y});
            operation.getEdges().forEach(edge => {
                edge.refresh();
            });
        },
        // 鼠标拖拽操作点结束
        onDragendNode(evt) {
            if (this.operations.indexOf(evt.item) == -1) {
                return;
            }

            const operation = evt.item; // 获取鼠标拖拽的节点元素对象
            let polygonModel = this.polygon.getModel();
            let operationModel = operation.getModel();
            if (operationModel.isVertex) {
                polygonModel.points[operationModel.index][0] = operationModel.x - polygonModel.x;
                polygonModel.points[operationModel.index][1] = operationModel.y - polygonModel.y;
            } else {
                polygonModel.points.splice(operationModel.index + 1, 0,
                    [operationModel.x - polygonModel.x, operationModel.y - polygonModel.y]);
            }
            this.polygon.refresh();

            this.operations.forEach(operation => {
                this.graph.removeItem(operation);
            });
            this.operationEdges.forEach(edge => {
                this.graph.removeItem(edge);
            });

            this.operations = this.createOperations(this.polygon);
        },
    });
})();