import "./axios.min.js"

const novaCatModal = document.getElementById('novaCat');
const editarCatModal = document.getElementById('editarCat');
const novoTopModal = document.getElementById('novoTop');
const editarTopModal = document.getElementById('editarTop');


async function exibirTopicosPorCategoria() {
    var cats = `<div class="accordion" id="catAccordion">
`;
    var catData = await axios.get('/interno/categorias').then((catResponse) => catResponse.data).catch(function (catError) {
        console.log(catError);
    });

    for (var i = 0; i < catData.length; i++) {
        cats += `  <div class="accordion-item">
    <h2 class="accordion-header" id="catHeader${catData[i].id}">
      <button type="button" class="accordion-button collapsed col" data-bs-toggle="collapse" data-bs-target="#catBody${catData[i].id}" id="catButton${catData[i].id}" aria-expanded="false" aria-controls="catBody${catData[i].id}">
        ${catData[i].nome}
      </button>
      <button type="button" class="btn btn-primary col" data-bs-toggle="modal" data-bs-target="#criarTop">
        Criar Tópico
      </button>
    </h2>
    <div id="catBody${catData[i].id}" class="accordion-collapse collapse" aria-labelledby="catHeader${catData[i].id}">
      <div class="accordion-body">
        <div class="row justify-content-center" id="catList${catData[i].id}">`;
        var topData = await axios.get(`/interno/topicos/cat/${catData[i].id}/`).then((topResponse) => topResponse.data).catch(function (topError) {
            console.log(topError);
        });

        for (var j = 0; j < topData.length; j++) {
            cats += `          <div class="col-4" id="top${topData[j].id}">
            <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#editarTop">
              <img id="topImagem${topData[j].id}" src="${topData[j].imagem}" class="img-thumbnail">
              <p id="topNome${topData[j].id}">${topData[j].nome}</p>
              <p hidden id="topCat${topData[j].id}">${catData[i].id}</p>
            </button>
          </div>
`
        }

        if (topData.length == 0)
            cats += "        <p class='text-start'>Ainda não há tópicos presentes</p>"

        cats += `        </div>
      </div>
    </div>
  </div>
`;
    }

    if (catData.length == 0)
        cats += `  <p class='text-start'>Ainda não há categorias presentes</p>
`;

    cats += `</div>`;
    document.getElementById('listaCat').innerHTML = cats;
}


function imprimeNovaCategoria(catData) {
    var id = catData.id;

    var catStr = `<div class="accordion-item">
  <h2 class="accordion-header" id="catHeader${id}">
    <button type="button" class"accordion-button collapsed col" data-bs-toggle="collapse" data-bs-target="#catBody${id}" id="catButton${id}" aria-expanded="false" aria-controls="catBody=${id}">
      Criar Tópico
    </button>
  </h2>
  <div id="catBody${id}" class "accordion-collapse collapse" aria-labelledby="catHeader${id}">
    <div class="accordion-body">
      <div class="row content-justify-center" id="catList${id}">
        <p class='text-start'>Ainda não há tópicos presentes</p>
      </div>
    </div>
  </div>
</div>`

    let list = document.getElementById('listaCat');

    if (list.innerHTML === `<p class='text-start'>Ainda não há categorias presentes</p>`)
        list.innerHTML = catStr;
    else
        list.innerHTML += catStr;
}

function imprimeNovoTopicoEmCategoria(topData) {
    var topStr = `<div class="col-4" id="top${topData.id}>
  <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#editarTop">
    <img src="${topData.imagem}" class="img-thumbnail">
    <p>${topData.nome}</p>
  </button>
</div>`

    let list = document.getElementById(`catList${topData.categoria}`);

    if (list.innerHTML === `<p class='text-start'>Ainda não há tópicos presentes</p>`)
        list.innerHTML = topStr;
    else
        list.innerHTML += topStr;
}


export function criarCategoria() {
    let nome = novaCatModal.querySelector('#novaCatNome').value;

    axios.post('/interno/categorias', {
        "nome": nome
    }).then(function (response) {
        imprimeNovaCategoria(response.data);
    }).catch(function (error) {
        console.log(error);
    });
}

export function editarCategoria() {
    let id = editarCatModal.querySelector('#editarCatId').value;
    let nome = editarCatModal.querySelector('#editarCatNome').value;

    axios.put(`/interno/categorias/${nome}`, {
        "nome": nome
    }).then(function (response) {
        document.getElementById(`catButton${id}`).innerHTML = response.data.nome;
    }).catch(function (error) {
        console.log(error);
    });
}

export function criarTopico() {
    let nome = novoTopModal.querySelector('#novoTopNome').value;
    let imagem = novoTopModal.querySelector('#novoTopImagem').value;
    let prioridade = novoTopModal.querySelector('#novoTopPrioridade').value;
    let categoria = novoTopModal.querySelector('#novoTopCat').value;

    const payload = {
        nome: nome,
        prioridade: prioridade
    };
    if (imagem !== "")
        payload[imagem] = imagem;
    if (categoria !== "")
        payload[categoria] = categoria;

    axios.post('/interno/topicos', payload).then(function (response) {
       if (response.data.categoria !== 'null')
           imprimeNovoTopicoEmCategoria(response.data);
    }).catch(function (error) {
       console.log(error);
    });
}

export function editarTopico() {
    let id = editarTopModal.querySelector('#editarTopId').value;
    let nome = editarTopModal.querySelector('#editarTopNome').value;
    let imagem = editarCatModal.querySelector('#editarTopImagem').value;
    let prioridade = editarTopModal.querySelector('#editarTopPrioridade').value;
    let categoria = editarTopModal.querySelector('#editarTopCategoria').value;
    let ativo = editarTopModal.querySelector('#editarTopAtivo').value;

    const payload = {
        id: id,
        nome: nome,
        prioridade: prioridade,
        ativo: ativo
    };
    if (imagem !== "")
        payload[imagem] = imagem;
    if (categoria !== "")
        payload[categoria] = categoria;

    axios.put(`/interno/topicos/${id}`, payload).then(function (response) {
        var topData = response.data;
        let div = document.getElementById(`top${id}`);

        if ((div != null) && (topData.categoria !== 'null')) {
            if (topData.categoria === (div.querySelector(`#topCat${id}`).value === topData.categoria)) {
                div.querySelector(`#topImagem${id}`).value = topData.imagem;
                div.querySelector(`#topNome${id}`).value = topData.nome;
            }
            else
                imprimeNovoTopicoEmCategoria(topData);
        }

        if ((div != null) && (div.querySelector(`#topCat${id}`).value !== topData.categoria))
            div.remove();
    }).catch(function (error) {
        console.log(error);
    });
}


exibirTopicosPorCategoria();