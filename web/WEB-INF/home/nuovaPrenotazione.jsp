<%--
  Created by IntelliJ IDEA.
  User: sergi
  Date: 13/06/2020
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Modal -->
<div class="modal fade" id="prenotazioneModal" tabindex="-1" role="dialog"  data-backdrop="static" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Nuova Prenotazione</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <div class='form-row justify-content-center'>
                        <div class="form-group col-md-6">
                            <label for="datePicker">Giorno della prenotazione</label>
                            <div class="input-group date" id="datePicker" data-target-input="nearest">
                                <input type="text" class="form-control datetimepicker-input" data-target="#datePicker" id="dateValue"/>
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
                                <input type="text" class="form-control datetimepicker-input" data-target="#timeStart" id="timeStartValue"/>
                                <div class="input-group-append" data-target="#timeStart" data-toggle="datetimepicker">
                                    <div class="input-group-text"><i class="far fa-clock"></i></div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group col-md-6 align-self-start">
                            <label for="timeEnd" class="col-sm-2 col-form-label">Alle:</label>
                            <div class="input-group date" id="timeEnd" data-target-input="nearest">
                                <input type="text" class="form-control datetimepicker-input" data-target="#timeEnd" id="timeEndValue"/>
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

