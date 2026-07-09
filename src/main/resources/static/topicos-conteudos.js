import "./axios.min.js"

async function exibirMateriaisPorConteudo() {
    const topicoId = document.getElementById('topicoId').value;

    if (!topicoId) return;

    var cats = `<div class="accordion" id="catAccordion">
`;
    var catData = await axios.get(`/interno/conteudos/lista/${topicoId}`).then((catResponse) => catResponse.data).catch(function (catError) {
        console.log(catError);
    });

    for (var i = 0; i < catData.length; i++) {
        cats += `  <div class="accordion-item">
    <h2 class="accordion-header" id="catHeader{catID}">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#catBody{catID}" aria-expanded="true" aria-controls="catBody{catID}">
        {catNOME}
      </button>
    </h2>
    <div id="catBody{catID}" class="accordion-collapse collapse" aria-labelledby="catHeader{catID}">
      <div class="accordion-body">

    
    <div class="d-flex justify-content-between align-items-center mb-3 border-bottom pb-2">
          <span class="text-muted">Materiais deste conteúdo</span>
          <button type="button" class="btn btn-sm btn-outline-primary" 
                  data-bs-toggle="modal" 
                  data-bs-target="#novoMat" 
                  data-bs-catid="{catID}">
            + Adicionar Material
          </button>
        </div>
`;
        var topData = await axios.get(`/interno/materiais/lista/${catData[i].id}`).then((topResponse) => topResponse.data).catch(function (topError) {
            console.log(topError);
        });

        for (var j = 0; j < topData.length; j++) {
            const matNOME = topData[j].nome;
            const matLINK = topData[j].link;
            if (!(j%3))
                cats += `        <div class="row justify-content-center">`

            if ((matLINK != null) && (matLINK != "")){
                cats += `          <div class="col-4">
                        <a href="${matLINK}" target="_blank">
                            <p>${matNOME}</p>
                        </a>
                    </div>
                    `
            } else {
                cats += `          <div class="col-4">
                            <p>${matNOME}</p>
                    </div>
                    `
            }
            if ((j%3 == 2) || (j == topData.length-1))
                cats += `        </div>`
        }

        if (topData.length == 0)
            cats += "        <p class='text-start'>Ainda não há materiais presentes</p>"

        cats = cats.replaceAll(`{catID}`, catData[i].id).replace(`{catNOME}`, catData[i].nome);
        cats += `      </div>
    </div>
  </div>
`;
    }

    if (catData.length == 0)
        cats += "  <p class='text-start'>Ainda não há conteúdos presentes</p>";

    cats += `</div>`;
    document.getElementById('listaCont').innerHTML = cats;
}

export function criarConteudo() {
    let nome = document.getElementById('novoContNome').value;
    let nivel = document.getElementById('novoContDificuldade').value;

    if (nome != ""){
        const topicoId = document.getElementById('topicoId').value;
        
        
        axios.post('/interno/conteudos', {
            "nome": nome,
            "nivel": nivel,
            "topico": {"id": topicoId}
        }).then(function (response) {
            exibirMateriaisPorConteudo();
        }).catch(function (error) {
            console.log(error);
        });
    }
}

export function criarMaterial() {
    let nome = document.getElementById('novoMatNome').value;
    let link = document.getElementById('novoMatLink').value;

    let conteudoId = document.getElementById('novoMat').getAttribute('data-current-cat-id');
    
    if (nome != ""){
        axios.post('/interno/materiais', {
            "nome": nome,
            "link": link,
            "conteudo": {"id": conteudoId}
        }).then(function (response) {
            exibirMateriaisPorConteudo();
        }).catch(function (error) {
            console.log(error);
        });
    }
}
    
// export function editarTopico() {}

exibirMateriaisPorConteudo();