<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Modal -->
<div class="modal fade" id="prenotazioneModal" tabindex="-1" role="dialog" data-backdrop="static"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Nuova Prenotazione</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row ">
                    <div class="col-12 text-center riepilogoPrenotazioni" style="display: none;">
                        <p class="font-weight-bold"> La postazione è già prenotata nelle seguenti fasce orarie: </p>
                        <span id="fasceOrarie"></span>
                    </div>
                </div>
                <form>
                    <%if(request.isUserInRole("BGT")){%>
                        <div class="form-row justify-content-center">
                            <div class="form-group col-md-12">
                                <label for="emailUtente">Inserire email dell'utente:</label>
                                <input class="form-control basicAutoComplete" id="emailUtente" type="text" autocomplete="on"
                                       data-noresults-text="Nessun utente trovato.">
                            </div>
                        </div>
                    <%}%>
                    <div class='form-row justify-content-center'>
                        <div class="form-group col-md-6">
                            <label for="datePicker">Giorno della prenotazione</label>
                            <div class="input-group date" id="datePicker" data-target-input="nearest">
                                <input type="text" class="form-control datetimepicker-input" data-target="#datePicker"
                                       id="dateValue"/>
                                <div class="input-group-append" data-target="#datePicker" data-toggle="datetimepicker">
                                    <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-6 align-self-start">
                            <label for="timeStart" class="col-sm-2 col-form-label">Dalle:</label>
                            <div class="input-group date" id="timeStart" data-target-input="nearest">
                                <input type="text" class="form-control datetimepicker-input" data-target="#timeStart"
                                       id="timeStartValue"/>
                                <div class="input-group-append" data-target="#timeStart" data-toggle="datetimepicker">
                                    <div class="input-group-text"><i class="far fa-clock"></i></div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group col-md-6 align-self-start">
                            <label for="timeEnd" class="col-sm-2 col-form-label">Alle:</label>
                            <div class="input-group date" id="timeEnd" data-target-input="nearest">
                                <input type="text" class="form-control datetimepicker-input" data-target="#timeEnd"
                                       id="timeEndValue"/>
                                <div class="input-group-append" data-target="#timeEnd" data-toggle="datetimepicker">
                                    <div class="input-group-text"><i class="far fa-clock"></i></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-row justify-content-center">
                        <div class="form-group col-md-12">
                            <label class="mr-sm-2" for="isPagato">Scegli dove pagare:</label>
                            <select class="custom-select mr-sm-2" id="isPagato">
                                <option value="true">Online</option>
                                <option value="false">In Biglietteria</option>
                            </select>
                        </div>
                    </div>
                </form>
                <div class="text-center">
                    <div class="spinner-border" id="loading" style="width: 4rem; height: 4rem; display: none;"
                         role="status">
                        <span class="sr-only">Loading...</span>
                    </div>
                </div>
                <div id="postazioneSelected" class="text-center">
                    <h3>Stai prenotando la postazione: </h3>
                    <h1 id="idPostazione">
                    </h1>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                <button type="button" class="btn btn-primary" onclick="sendPrenotazione()">Prenota</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/prenotazioni.js"></script>

