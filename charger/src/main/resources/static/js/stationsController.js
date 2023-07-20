
/*
//---------------------------------------------------------------------------//
                                Station
//---------------------------------------------------------------------------//
*/

function createElementFromAttribute(attribute, parent) {
    const openCell = document.createElement("td");
    openCell.innerHTML = `<p> ${attribute} </p>`;
    parent.appendChild(openCell);
}

function createButtons(parent,data) {

    const buttonsBook = document.createElement("td");
    buttonsBook.innerHTML = `<button type="button" class="btn btn-success" onclick="showDialog(${data.id})">Book now!</button>`;
    parent.appendChild(buttonsBook);

    const buttonsUpdate = document.createElement("td");
    buttonsUpdate.innerHTML = `<button type="button" class="btn btn-primary" onclick="showDialogUpdateStation(${data.id})">Update</button>`;
    parent.appendChild(buttonsUpdate);

    const buttonsDelete = document.createElement("td");
    buttonsDelete.innerHTML = `<button type="button" class="btn btn-danger" onclick="deleteStation(${data.id})">Delete</button>`;
    parent.appendChild(buttonsDelete);

}


const baseURL ='http://localhost:8090';

//Station: Table

$(document).ready(async function(){
    const responseJson = await fetch(
        baseURL + `/api/stations`,
        {
            method: 'GET',
            headers: {
                    'Content-Type':'application/json'
                        }
        }
)
    const response = await responseJson.json();
        if(responseJson.ok){
            console.log(response);
            const table = $("#station-table tbody");
            for(const station of response){
                const newStationTr=document.createElement("tr");

                createElementFromAttribute(station.id,newStationTr);
                createElementFromAttribute(station.location,newStationTr);
                createElementFromAttribute(station.open,newStationTr);
                createElementFromAttribute(station.nameStation,newStationTr);
                createElementFromAttribute(station.stationType.id,newStationTr);
                createButtons(newStationTr,station);

                table.append(newStationTr);
                }
            }
});

//Station: Update - autofill

var isTrueSet = (data.open === 'true'); //ca open sa fie boolean, nu string

async function showDialogUpdateStation(id){
    console.log(id);
    const data = await fetchRecordStation(id);

    $("#stationId").val(data.id);
    $("#StationTypeIdDialog").val(data.stationTypeId);
    $("#nameStation").val(data.nameStation);
    $("#location").val(data.location);
    $("#open").val(isTrueSet);

    const myModalEl = document.getElementById('updateStationDialog');
    const modal = bootstrap.Modal.getOrCreateInstance(myModalEl);
    modal.show();
}

async function fetchRecordStation(id) {
    const responseJson = await fetch(
        baseURL + '/api/stations/' + id,
        {
            method: 'GET',
            headers:{
            'Content-Type':'application/json'
            },
         });
         const response = await responseJson.json();
    return response;
 }

//Station: Update

async function updateStation(){
    const data={
        id: $('#stationId').val(),
        nameStation:$('#nameStation').val(),
        location: $('#location').val(),
        open: $('#open').val(),
       
        stationTypeId:$('#StationTypeIdDialog').val()

    };

    debugger

    const responseJson = await fetch(
        baseURL + '/api/stations',
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



//Station: Delete

async function deleteStation(id){
    debugger
    const responseJson = await fetch(
        baseURL + '/api/stations/' + id,
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


//Search Bar

async function searchStation(){
    const name_field = $(searchItem).val();
    debugger
    const responseJson = await fetch(
    baseURL + '/api/stations/nameStation/' + name_field,
    {
        method: 'GET',
        headers:{
            'Content-Type':'application/json'
        },
    });
    const response = await responseJson.json();
        if(responseJson.ok){
                console.log(response);
                const table = $("#station-table tbody");

                table.empty();
                for(const station of response){
                    //table.
                    const newStationTr = document.createElement("tr");

                    createElementFromAttribute(station.id,newStationTr);
                    createElementFromAttribute(station.location,newStationTr);
                    createElementFromAttribute(station.open,newStationTr);
                    createElementFromAttribute(station.nameStation,newStationTr);
                    createElementFromAttribute(station.stationType.id,newStationTr);
                    createButtons(newStationTr,station);

                    table.append(newStationTr);
                }
            }
            else{
                console.log("An error has occured", response);
            }
}


//Sort

async function sortStation(field,direction){
debugger

    const responseJson = await fetch(
    baseURL + `/api/stations/sorted/${field}/${direction}` ,
    {
    method: 'GET',
    headers:{
    'Content-Type':'application/json'
    },
    });
    const response = await responseJson.json();
    if(responseJson.ok){
            console.log(response);
            const table = $("#station-table tbody");


            table.empty();
            for(const station of response){
                const newStationTr = document.createElement("tr");

                createElementFromAttribute(station.id,newStationTr);
                createElementFromAttribute(station.location,newStationTr);
                createElementFromAttribute(station.open,newStationTr);
                createElementFromAttribute(station.nameStation,newStationTr);
                createElementFromAttribute(station.stationType.id,newStationTr);
                createButtons(newStationTr,station);

                table.append(newStationTr);
            }
        }
        else{
            console.log("An error has occured", response);
        }
}



/*
//---------------------------------------------------------------------------//
                                Bookings
//---------------------------------------------------------------------------//
*/


function showDialog(stationId){
    console.log(stationId);
    $("#bookingStationIdDialog").val(stationId);
    const myModalEl = document.getElementById('createBookingDialog');
    const modal = bootstrap.Modal.getOrCreateInstance(myModalEl);
    modal.show();
}

//Booking: Create

async function createBooking(){
    const data={

        namePerson: $('#namePerson').val(),
        licensePlate:$('#licensePlate').val(),
        stationId:$('#bookingStationIdDialog').val(),
        startDate: $('#startDate').val(),
        duration:parseInt( $('#duration').val())

    };
    debugger

    const responseJson = await fetch(
        baseURL + '/api/bookings',
        {
        method: 'POST',
        headers:{
        'Content-Type':'application/json'
        },
        body: JSON.stringify(data)
        });
        const response = await responseJson.json();
        console.log(response);
    }