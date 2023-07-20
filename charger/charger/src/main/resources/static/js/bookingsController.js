function createElementFromAttribute(attribute, parent) {
    const openCell = document.createElement("td");
    openCell.innerHTML = `<p> ${attribute} </p>`;
    parent.appendChild(openCell);
}

const baseURL ='http://localhost:8090';
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
    const response = await responseJson.json();
    if(responseJson.ok){
        console.log(response);
        const table = $("#bookings-table tbody");
        for(const bookings of response){
            const newBookingTr=document.createElement("tr");
            createElementFromAttribute(bookings.id,newBookingTr);
            createElementFromAttribute(bookings.namePerson,newBookingTr);
            createElementFromAttribute(bookings.licensePlate,newBookingTr);
            createElementFromAttribute(bookings.stationId,newBookingTr);
            createElementFromAttribute(bookings.startTime,newBookingTr);
            createElementFromAttribute(bookings.duration,newBookingTr);
            createButtons(newBookingTr);
            table.append(newBookingTr);
            }
        }
});
