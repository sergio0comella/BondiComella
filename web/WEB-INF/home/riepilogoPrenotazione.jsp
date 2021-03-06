
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Modal -->
<div class="modal fade" id="riepilogoPrenotazione" tabindex="-1" role="dialog" data-backdrop="static"
     aria-labelledby="riepilogo" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="riepilogo">Riepilogo Prenotazione</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <h6>Utente: <span  class="font-weight-bold" id="utentePrenotazione"></span></h6>
                <h6>Valida il giorno: <span class="font-weight-bold" id="dataPrenotazione"></span> </h6>
                <h6>Dalle ore: <span class="font-weight-bold" id="oraInizio"></span> alle ore: <span class="font-weight-bold" id="oraFine"></span></h6>
                <h6>Pagata:<span class="font-weight-bold" id="statoPagamento"></span></h6>
            </div>
            <%if(request.isUserInRole("BGN")){%>
            <div class="modal-footer">
                <span class="idPrenotazione"></span>
                <button type="button" id="confermaPrenotazione" class="btn btn-info" onclick="confermaPrenotazione()">Conferma</button>
            </div>
            <%}else if(request.isUserInRole("BGT")){%>
            <div class="modal-footer">
                <span class="idPrenotazione"></span>
                <button type="button" id="pagaPrenotazione" class="btn btn-info" onclick="pagaPrenotazione()">Conferma pagamento</button>
            </div>
            <%}%>
        </div>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/prenotazioni.js"></script>

