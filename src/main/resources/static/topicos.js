import "./axios.min.js"

const novaCatModal = document.getElementById('novaCat');
const editarCatModal = document.getElementById('editarCat');
const deletarCatModal = document.getElementById('deletarCat');
const novoTopModal = document.getElementById('novoTop');
const editarTopModal = document.getElementById('editarTop');
const deletarTopModal = document.getElementById('deletarCat');


async function exibirTopicosPorCategoria() {
    let cats = `<div class="accordion" id="catAccordion">
`;
    const catData = await axios.get('/interno/categorias').then((catResponse) => catResponse.data).catch(function (catError) {
        console.log(catError);
    });

    for (let i = 0; i < catData.length; i++) {
        cats += `  <div class="accordion-item" id="cat${catData[i].id}">
    <h2 class="accordion-header" id="catHeader${catData[i].id}">
      <button type="button" class="accordion-button collapsed col" data-bs-toggle="collapse" data-bs-target="#catBody${catData[i].id}" id="catButton${catData[i].id}" aria-expanded="false" aria-controls="catBody${catData[i].id}">
        ${catData[i].nome}
      </button>
      <button type="button" class="btn btn-primary col" data-bs-toggle="modal" data-bs-target="#criarTop" data-bs-cat="${catData[i].id}">
        Criar Tópico
      </button>
    </h2>
    <div id="catBody${catData[i].id}" class="accordion-collapse collapse" aria-labelledby="catHeader${catData[i].id}">
      <div class="accordion-body">
        <div class="row justify-content-center" id="catList${catData[i].id}">`;
        const topData = await axios.get(`/interno/topicos/cat/${catData[i].id}/`).then((topResponse) => topResponse.data).catch(function (topError) {
            console.log(topError);
        });

        for (let j = 0; j < topData.length; j++) {
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


function exibirNovaCategoria(catData) {
    const id = catData.id;

    const catStr = `<div class="accordion-item" id="cat${id}">
  <h2 class="accordion-header" id="catHeader${id}">
    <button type="button" class"accordion-button collapsed col" data-bs-toggle="collapse" data-bs-target="#catBody${id}" id="catButton${id}" aria-expanded="false" aria-controls="catBody=${id}">
      ${catData.nome}
    </button>
    <button type="button" class="btn btn-primary col" data-bs-toggle="modal" data-bs-target="#criarTop" data-bs-cat="${id}">
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

    const list = document.getElementById('listaCat');

    if (list.innerHTML === `<p class='text-start'>Ainda não há categorias presentes</p>`)
        list.innerHTML = catStr;
    else
        list.innerHTML += catStr;
}

function exibirNovoTopicoEmCategoria(topData) {
    const topStr = `<div class="col-4" id="top${topData.id}>
  <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#editarTop">
    <img src="${topData.imagem}" class="img-thumbnail">
    <p>${topData.nome}</p>
  </button>
</div>`

    const list = document.getElementById(`catList${topData.categoria}`);

    if (list.innerHTML === `<p class='text-start'>Ainda não há tópicos presentes</p>`)
        list.innerHTML = topStr;
    else
        list.innerHTML += topStr;
}


export function criarCategoria() {
    const nome = novaCatModal.querySelector('#novaCatNome').value;

    axios.post('/interno/categorias', {
        "nome": nome
    }).then(function (response) {
        bootstrap.Modal.getInstance(novaCatModal).hide();
        exibirNovaCategoria(response.data);
    }).catch(function (error) {
        console.log(error);
    });
}

export function editarCategoria() {
    const id = editarCatModal.querySelector('#editarCatId').value;
    const nome = editarCatModal.querySelector('#editarCatNome').value;

    axios.put(`/interno/categorias/${id}`, {
        "nome": nome
    }).then(function (response) {
        document.getElementById(`catButton${id}`).innerHTML = response.data.nome;
    }).catch(function (error) {
        console.log(error);
    });
}

export function deletarCategoria() {
    const id = deletarCatModal.querySelector('#deletarCatId').value;

    axios.delete(`/interno/categorias/${id}`).then(function () {
        document.getElementById(`cat${id}`).remove();
    }).catch(function (error) {
        console.log(error);
    });
}

export function criarTopico() {
    const nome = novoTopModal.querySelector('#novoTopNome').value;
    const imagem = novoTopModal.querySelector('#novoTopImagem').value;
    const prioridade = novoTopModal.querySelector('#novoTopPrioridade');
    const categoria = novoTopModal.querySelector('#novoTopCat').value;

    const payload = {
        nome: nome,
        prioridade: prioridade.options[prioridade.selectedIndex].text,
    };
    if (imagem !== "")
        payload[imagem] = imagem;
    if (categoria !== "0")
        payload[categoria] = categoria;

    axios.post('/interno/topicos', payload).then(function (response) {
        bootstrap.Modal.getInstance(novoTopModal).hide();

        if (response.data.categoria !== 'null')
            exibirNovoTopicoEmCategoria(response.data);
    }).catch(function (error) {
        console.log(error);
    });
}

export function editarTopico() {
    const id = editarTopModal.querySelector('#editarTopId').value;
    const nome = editarTopModal.querySelector('#editarTopNome').value;
    const imagem = editarCatModal.querySelector('#editarTopImagem').value;
    const prioridade = editarTopModal.querySelector('#editarTopPrioridade');
    const categoria = editarTopModal.querySelector('#editarTopCategoria').value;
    const ativo = editarTopModal.querySelector('#editarTopAtivo').value;

    const payload = {
        id: id,
        nome: nome,
        prioridade: prioridade.options[prioridade.selectedIndex].text,
        ativo: ativo
    };
    if (imagem !== "")
        payload[imagem] = imagem;
    if (categoria !== "0")
        payload[categoria] = categoria;

    axios.put(`/interno/topicos/${id}`, payload).then(function (response) {
        const topData = response.data;
        let div = document.getElementById(`top${id}`);

        if (topData.categoria !== 'null') {
            if ((div != null) &&
                (topData.categoria === div.querySelector(`#topCat${id}`).value)) {
                div.querySelector(`#topImagem${id}`).value = topData.imagem;
                div.querySelector(`#topNome${id}`).value = topData.nome;
            }
            else
                exibirNovoTopicoEmCategoria(topData);
        }

        if ((div != null) && (div.querySelector(`#topCat${id}`).value !== topData.categoria))
            div.remove();
    }).catch(function (error) {
        console.log(error);
    });
}

export function deletarTopico() {
    const id = deletarTopModal.querySelector('#deletarTopId').value;

    axios.delete(`interno/topicos/${id}`).then(function () {
        let div = document.getElementById(`top${id}`);

        if (div != null)
            div.remove();
    }).catch(function (error) {
        console.log(error);
    });
}


exibirTopicosPorCategoria();