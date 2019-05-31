

<%@page import="pe.kamwha.model.DetalleBoletaModel"%>
<%@page import="pe.kamwha.model.BoletaModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    BoletaModel boleta = (BoletaModel) request.getAttribute("venta");
    float total = 0;
    HttpSession sesionOK = request.getSession();
    System.out.println("ENTRO A carro.JSP");
%>

<jsp:include page="WEB-INF/partials-dynamic/head.jsp">
    <jsp:param name="title" value="Factura de Venta" />
</jsp:include>
<link href="css/carro.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="l-main ed-container">
        <div class="ed-item">
            <div class="ed-container web-80">
                <div class="ed-item web-80">
                    <h1>Factura de Venta</h1>
                </div>
            </div>
            <div class="ed-container offset-25 web-50 contact-form">
                <div class="ed-item web-30">
                    <label for="factura">Factura Número: </label>
                </div>
                <div class="ed-item web-70">
                    <input type="text" id="factura" readonly value="<%= boleta.getBoletaID()%>">
                </div>
                <div class="ed-item web-30">
                    <label for="fecha">Fecha:</label>
                </div>
                <div class="ed-item web-70">
                    <input type="text" id="fecha" readonly value="<%= boleta.getBoletaFecha()%>">
                </div>
                <div class="ed-item web-30">
                    <label for="nombre">Nombre:</label>
                </div>
                <div class="ed-item web-70">
                    <input type="text" id="nombre" readonly value="<%= boleta.getCliente().getClienteNombres()%>">
                </div>
                <div class="ed-item web-30">
                    <label for="apellidoPaterno">Apellidos:</label>
                </div>
                <div class="ed-item web-70">
                    <input type="text" id="apellidos" readonly value="<%= boleta.getCliente().getClienteApePaterno() + " " + boleta.getCliente().getClienteApeMaterno()%>">
                </div>
                <div class="ed-item web-30">
                    <label for="dni">D.N.I.:</label>
                </div>
                <div class="ed-item web-70">
                    <input type="text" id="dni" readonly value="<%= boleta.getCliente().getClienteDni()%>">
                </div>
            </div>

            <div class="ed-item offset-20 web-60">
                <table class="listado">
                    <thead>
                        <tr>
                            <th>Menu</th>
                            <th>Descripción</th>
                            <th>Valor</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for(DetalleBoletaModel detalle: boleta.getDetalle()){
                                total += detalle.getMenu().getMenuPrecio();
                        %>
                        <tr>
                            <td><%= detalle.getMenu().getMenuNombre() %></td>
                            <td><%= detalle.getMenu().getMenuDescripcion() %></td>
                            <td><%= detalle.getMenu().getMenuPrecio() %></td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="2" class="suma-label">Total</td>
                            <td class="suma"><%= total %></td>
                        </tr>
                    </tfoot>
                </table>
            </div>
                        
                        
        </div>
    </div>


    <div class="container">            
        <jsp:include page="WEB-INF/partials-dynamic/menu-navegacion.jsp">
            <jsp:param name="sesionOK" value="<%= sesionOK%>" />
        </jsp:include>
    </div>


    <section class="main">



    </section>

    <script src="js/classie.js"></script>
    <script src="js/gnmenu.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <script type="text/javascript" src="js/menu.js"></script>
    <script type="text/javascript">
        $(function () {

            Menu.init();

        });
    </script>
    <script>
        new gnMenu(document.getElementById('gn-menu'));
    </script>
    <script type="text/javascript">

        function DropDown(el) {
            this.dd = el;
            this.initEvents();
        }
        DropDown.prototype = {
            initEvents: function () {
                var obj = this;

                obj.dd.on('click', function (event) {
                    $(this).toggleClass('active');
                    event.stopPropagation();
                });
            }
        }

        $(function () {

            var dd = new DropDown($('#dd'));

            $(document).click(function () {
                // all dropdowns
                $('.wrapper-dropdown-2').removeClass('active');
            });

        });

    </script>
</body>
</html>

