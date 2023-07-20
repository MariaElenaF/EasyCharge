/*
//---------------------------------------------------------------------------//
                                Bookings
//---------------------------------------------------------------------------//
*/

function createElementFromAttribute(attribute, parent) {
    const openCell = document.createElement("td");
    openCell.innerHTML = `<p> ${attribute} </p>`;
    parent.appendChild(openCell);
}

function createButtons(parent,data) {

    const buttonsUp = document.createElement("td");
    buttonsUp.innerHTML = `<button type="button" class="btn btn-primary" onclick="showDialog(${data.id})">Update</button>`;
    parent.appendChild(buttonsUp);

    const buttonsDel = document.createElement("td");
    buttonsDel.innerHTML = `<button type="button" class="btn btn-danger" onclick="deleteBooking(${data.id})">Delete</button>`;
    parent.appendChild(buttonsDel);
}

const baseURL ='http://localhost:8090';

//Bookings: Table

$(document).ready(async function(){
    const responseJson = await fetch(
        baseURL + `/api/bookings`,
        {
            method: 'GET',
            headers: {
                    'Content-Type':'application/json'
                        }
        }
)
debugger
    const response = await responseJson.json();
    if(responseJson.ok){
        console.log(response);
        const table = $("#bookings-table tbody");
        for(const bookings of response){

            const newBookingTr=document.createElement("tr");
            createElementFromAttribute(bookings.id,newBookingTr);
            createElementFromAttribute(bookings.namePerson,newBookingTr);
            createElementFromAttribute(bookings.licensePlate,newBookingTr);
            createElementFromAttribute(bookings.stationId.id,newBookingTr);
            createElementFromAttribute(bookings.startDate,newBookingTr);
            createElementFromAttribute(bookings.endDate,newBookingTr);
            createButtons(newBookingTr,bookings);
            table.append(newBookingTr);
            }
        }
});


//Booking: Update: Autofill

async function showDialog(id){
    console.log(id);
    const data = await fetchRecord(id);
    $("#id").val(data.id);
    $("#namePerson").val(data.namePerson);
    $("#licensePlate").val(data.licensePlate);
    $("#bookingStationIdDialog").val(data.stationId);
    $("#startDate").val(data.startDate);
    $("#duration").val(data.duration);
    const myModalEl = document.getElementById('updateBookingDialog');
    const modal = bootstrap.Modal.getOrCreateInstance(myModalEl);
    modal.show();
}

async function fetchRecord(id) {
    const responseJson = await fetch(
        baseURL + '/api/bookings/' + id,
        {
            method: 'GET',
            headers:{
            'Content-Type':'application/json'
            },
         });
         const response = await responseJson.json();
    return response;
 }

 //Booking: Update

async function updateBooking(){
    const data={
        id: $('#id').val(),
        namePerson: $('#namePerson').val(),
        licensePlate:$('#licensePlate').val(),
        stationId:parseInt($('#bookingStationIdDialog').val()),
        startDate: $('#startDate').val(),
        duration:parseInt( $('#duration').val())
    };

    debugger

    const responseJson = await fetch(
        baseURL + '/api/bookings',
        {
            method: 'PUT',
            headers:{
            'Content-Type':'application/json'
             },
            body: JSON.stringify(data)
        });
        window.location.reload();
        const response = await responseJson.json();
        console.log(response);
    }

//Booking: Delete

async function deleteBooking(id){
        debugger
    const responseJson = await fetch(
        baseURL + '/api/bookings/' + id,
        {
            method: 'DELETE',
            headers:{
            'Content-Type':'application/json'
                },
        });
        window.location.reload();
        const response = await responseJson.json();
    console.log(response);
}