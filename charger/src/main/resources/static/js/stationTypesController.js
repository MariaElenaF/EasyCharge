/*
//---------------------------------------------------------------------------//
                            Station Type
//---------------------------------------------------------------------------//
*/
function createElementFromAttribute(attribute, parent) {
    const openCell = document.createElement("td");
    openCell.innerHTML = `<p> ${attribute} </p>`;
    parent.appendChild(openCell);
}

function createButtonsStation(parent,data) {

    const buttonsStation = document.createElement("td");
    buttonsStation.innerHTML = `<button type="button" class="btn btn-success" onclick="showDialogStation(${data.id})">Add station</button>`;
    parent.appendChild(buttonsStation);

    const buttonsUpdateST = document.createElement("td");
    buttonsUpdateST.innerHTML = `<button type="button" class="btn btn-primary" onclick="showDialogUpdateStationType(${data.id})">Update station type</button>`;
    parent.appendChild(buttonsUpdateST);

    const buttonsDeleteST = document.createElement("td");
    buttonsDeleteST.innerHTML = `<button type="button" class="btn btn-danger" onclick="deleteStationType(${data.id})">Delete station type</button>`;
    parent.appendChild(buttonsDeleteST);

}


const baseURL ='http://localhost:8090';


//Station Type: Table

$(document).ready(async function(){
    const responseJson = await fetch(
        baseURL + `/api/stationTypes`,
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
            const table = $("#stationType-table tbody");
            for(const stationType of response){
                const newStationTr=document.createElement("tr");
                createElementFromAttribute(stationType.id,newStationTr);
                createElementFromAttribute(stationType.name,newStationTr);
                createElementFromAttribute(stationType.plugType,newStationTr);
                createElementFromAttribute(stationType.power,newStationTr);
                createButtonsStation(newStationTr,stationType);
                table.append(newStationTr);
                }
            }
});

function showDialogStationType(){
    console.log();
    const myModalEl = document.getElementById('createStationTypeDialog');
    const modal = bootstrap.Modal.getOrCreateInstance(myModalEl);
    modal.show();
}

//Station Type: Create

async function createStationType(){
    const data={

        name: $('#name').val(),
        plugType:$('#plugType').val(),
        power:$('#power').val()

    }

    debugger

        const responseJson = await fetch(
            baseURL + '/api/stationTypes',
            {
            method: 'POST',
            headers:{
            'Content-Type':'application/json'
            },
            body: JSON.stringify(data)
            });
            window.location.reload();
            const response = await responseJson.json();
            console.log(response);

}

//Station Type: Update-autofill

async function showDialogUpdateStationType(id){
    console.log(id);
    const data = await fetchRecordStationType(id);

    $("#stationTypeIdUp").val(data.id);
    $("#nameUp").val(data.name);
    $("#plugTypeUp").val(data.plugType);
    $("#powerUp").val(data.power);

    const myModalEl = document.getElementById('updateStationTypeDialog');
    const modal = bootstrap.Modal.getOrCreateInstance(myModalEl);
    modal.show();
}

async function fetchRecordStationType(id) {
    const responseJson = await fetch(
        baseURL + '/api/stationTypes/' + id,
        {
            method: 'GET',
            headers:{
            'Content-Type':'application/json'
            },
         });
         const response = await responseJson.json();
    return response;
 }

//Station Type: Update

async function updateStationType(){
    const data={
        id: $('#stationTypeIdUp').val(),
        name: $('#nameUp').val(),
        plugType:$('#plugTypeUp').val(),
        power:parseInt($('#powerUp').val())

    };

    debugger

    const responseJson = await fetch(
        baseURL + '/api/stationTypes',
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

//Station Type: Delete

async function deleteStationType(id){
    debugger
    const responseJson = await fetch(
        baseURL + '/api/stationTypes/' + id,
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




    /*
//---------------------------------------------------------------------------//
                                Station
//---------------------------------------------------------------------------//
*/


    function showDialogStation(stationType){
        console.log(stationType);
        $("#StationTypeIdDialog").val(stationType);
        const myModalEl = document.getElementById('createStationDialog');
        const modal = bootstrap.Modal.getOrCreateInstance(myModalEl);
        modal.show();
    }
    
//Station: Create

    async function createStation(){
        const data={
           // id:$('#stationId').val(),
            nameStation: $('#nameStation').val(),
            location:$('#location').val(),
            open: $('#open').val(),
            stationTypeId:$('#StationTypeIdDialog').val()
    
        }
    
        debugger
    
            const responseJson = await fetch(
                baseURL + '/api/stations',
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