import "./axios.min.js"

async function exibirTopicosPorCategoria() {
    var cats = `<div class="accordion" id="catAccordion">
`;
    var catData = await axios.get('/interno/categorias').then((catResponse) => catResponse.data).catch(function (catError) {
        console.log(catError);
    });

    for (var i = 0; i < catData.length; i++) {
        cats += `  <div class="accordion-item">
    <h2 class="accordion-header" id="catHeader{catID}">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#catBody{catID}" aria-expanded="true" aria-controls="depBody{depID}">
        {catNOME}
      </button>
    </h2>
    <div id="depBody{catID}" class="accordion-collapse collapse" aria-labelledby="catHeader{catID}">
      <div class="accordion-body">
`;
//         var topData = await axios.get(`/interno/topicos/cat/${catData[i].id}/`).then((topResponse) => topResponse.data).catch(function (topError) {
//             console.log(topError);
//         });

//         for (var j = 0; j < topData.length; j++) {
//             if (!(j%3))
//                 cats += `        <div class="row justify-content-center">`

//             cats += `          <div class="col-4">
//             <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#editarTop">
//               <img src="{topIMG}" class="img-thumbnail">
//               <p>{topNOME}</p>
//             </button>
//           </div>
// `
//             cats = cats.replace(`{topIMG}`, topData[j].imagem).replace(`{topNOME}`, topData[j].nome);

//             if ((j%3 == 2) || (j == topData.length-1))
//                 cats += `        </div>`
//         }

//         if (topData.length == 0)
//             cats += "        <p class='text-start'>Ainda não há tópicos presentes</p>"

        cats = cats.replaceAll(`{catID}`, catData[i].id).replace(`{catNOME}`, catData[i].nome);
        cats += `      </div>
    </div>
  </div>
`;
    }

    if (catData.length == 0)
        cats += "  <p class='text-start'>Ainda não há categorias presentes</p>";

    cats += `</div>`;
    document.getElementById('listaCat').innerHTML = cats;
}

export function criarCategoria() {
    let nome = document.getElementById('novaCatNome').value;

    axios.post('/interno/categorias', {
        "nome": nome
    }).then(function (response) {
        exibirTopicosPorCategoria();
    }).catch(function (error) {
        console.log(error);
    });
}

// export function editarTopico() {}

exibirTopicosPorCategoria();