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
                                <input type="text" class="form-control datetimepicker-input" data-target="#datePicker"/>
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
                                <input type="text" class="form-control datetimepicker-input" data-target="#timeStart"/>
                                <div class="input-group-append" data-target="#timeStart" data-toggle="datetimepicker">
                                    <div class="input-group-text"><i class="far fa-clock"></i></div>
                                </div>
                            </div>
                        </div>
                    <div class="form-group col-md-6 align-self-start">
                        <label for="timeEnd" class="col-sm-2 col-form-label">Alle:</label>
                        <div class="input-group date" id="timeEnd" data-target-input="nearest">
                            <input type="text" class="form-control datetimepicker-input" data-target="#timeEnd"/>
                            <div class="input-group-append" data-target="#timeEnd" data-toggle="datetimepicker">
                                <div class="input-group-text"><i class="far fa-clock"></i></div>
                            </div>
                        </div>
                    </div>
                    </div>
                </form>
                <div id="postazioneSelected">
                    <span id="idPostazione">

                    </span>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
                <button type="button" class="btn btn-primary" id="sendPrenotazione">Prenota</button>
            </div>
        </div>
    </div>
</div>

<script>
    $.fn.datetimepicker.Constructor.Default = $.extend({}, $.fn.datetimepicker.Constructor.Default, {
        icons: {
            time: 'far fa-clock',
            date: 'far fa-calendar',
            up: 'fas fa-arrow-up',
            down: 'fas fa-arrow-down',
            previous: 'fas fa-chevron-left',
            next: 'fas fa-chevron-right',
            today: 'far fa-calendar-check-o',
            clear: 'far fa-trash',
            close: 'far fa-times'
        } });

    $(function () {
        let timeStart = $("#timeStart");
        let timeEnd = $("#timeEnd");
        let datePicker = $("#datePicker");

        datePicker.datetimepicker({
            locale: 'it',
            useCurrent: false,
            format: 'DD/MM/YYYY',
            stepping: 60,
            minDate: Date.now(),
        });

        timeStart.datetimepicker({
            format: 'LT',
            stepping: 60,
            locale: 'it',
            minDate: Date.now()
        });
        timeEnd.datetimepicker({
            format: 'LT',
            stepping: 60,
            locale: 'it',
            minDate: Date.now()
        })


        timeStart.on("change.datetimepicker", function (e) {
            timeEnd.datetimepicker('minDate', e.date.add(1, 'h'));
        });
        timeEnd.on("change.datetimepicker", function (e) {
            timeStart.datetimepicker('maxDate', e.date.subtract(1, 'h'));
        });
    });

</script>
