import "./axios.min.js"

const novaCatModal = document.getElementById('novaCat');
const editarCatModal = document.getElementById('editarCat');
const excluirCatModal = document.getElementById('excluirCat');
const novoTopModal = document.getElementById('novoTop');
const editarTopModal = document.getElementById('editarTop');
const excluirTopModal = document.getElementById('excluirCat');


async function exibirTopicosPorCategoria() {
    let cats = `<div class="accordion" id="catAccordion">
`;
    const catData = await axios.get('/interno/categorias').then((catResponse) => catResponse.data).catch(function (catError) {
        console.log(catError);
    });

    for (let i = 0; i < catData.length; i++) {
        cats += `  <div class="accordion-item" id="cat${catData[i].id}">
    <h2 class="accordion-header" id="catHeader${catData[i].id}">
      <button type="button" class="accordion-button col collapsed" data-bs-toggle="collapse" data-bs-target="#catBody${catData[i].id}" id="catButton${catData[i].id}" aria-expanded="false" aria-controls="catBody${catData[i].id}">
        ${catData[i].nome}
      </button>
    </h2>
    <div id="catBody${catData[i].id}" class="accordion-collapse collapse" aria-labelledby="catHeader${catData[i].id}">
      <div class="accordion-body">
        <div class="d-flex justify-content-between align-items-center mb-3 border-bottom pb-2">
          <div class="col-lg-auto">
            <span class="text-muted">Tópicos desta categoria</span>
            <button type="button" class="btn btn-sm btn-outline-primary ms-md-2" data-bs-toggle="modal" data-bs-target="#novoTop" data-bs-cat="${catData[i].id}">
              + Adicionar Tópico
            </button>
          </div>
          <div class="col-lg-auto ms-auto">
            <button type="button" id="editarCat${catData[i].id}" class="btn btn-sm btn-outline-secondary me-md-2" data-bs-toggle="modal" data-bs-target="#editarCat" data-bs-id="${catData[i].id}" data-bs-nome="${catData[i].nome}">
              ! Editar Categoria
            </button>
          </div>
          <div class="col-lg-auto">
            <button type="button" id="excluirCat${catData[i].id}" class="btn btn-sm btn-outline-danger" data-bs-toggle="modal" data-bs-target="#excluirCat" data-bs-id="${catData[i].id}" data-bs-nome="${catData[i].nome}">
              - Excluir Categoria
            </button>
          </div>
        </div>
        <div class="row justify-content-center" id="catList${catData[i].id}">`;
        let topData = await axios.get(`/interno/topicos/cat/${catData[i].id}`).then((topResponse) => topResponse.data).catch(function (topError) {
            console.log(topError);
        });

        for (let j = 0; j < topData.length; j++) {
            cats += `          <div class="col-4" id="top${topData[j].id}">
            <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#editarTop" data-bs-id="${topData[j].id}">
              <img id="topImagem${topData[j].id}" src="${topData[j].imagem}" class="img-fluid img-thumbnail">
              <p id="topNome${topData[j].id}">${topData[j].nome}</p>
              <p hidden id="topCat${topData[j].id}">${catData[i].id}</p>
            </button>
          </div>
`
        }

        if (topData.length == 0)
            cats += `        <p id=semTop${catData[i].id} class='text-start'>Ainda não há tópicos presentes</p>`;

        cats += `        </div>
      </div>
    </div>
  </div>
`;
    }

    if (catData.length == 0)
        cats += `  <p id=semCat class='text-start'>Ainda não há categorias presentes</p>
`;

    cats += `</div>`;
    document.getElementById('listaCat').innerHTML = cats;
}

function exibirOpcoesCategoria() {
    let select = `<option value="0">Nenhuma</option>
`;

    axios.get('/interno/categorias').then(function (response) {
        const data = response.data;

        for (let i = 0; i < data.length; i++)
            select += `<option id="novoTopOpcao${data[i].id}" value="${data[i].id}">${data[i].nome}</option>
`;

        document.getElementById('novoTopCat').innerHTML = select;
        document.getElementById('editarTopCat').innerHTML = select;
    }).catch(function (catError) {
        console.log(catError);
    });
}


function exibirNovaCategoria(catData) {
    const id = catData.id;
    const nome = catData.nome;

    const catStr = `<div class="accordion-item" id="cat${id}">
  <h2 class="accordion-header" id="catHeader${id}">
    <button type="button" class="accordion-button col collapsed" data-bs-toggle="collapse" data-bs-target="#catBody${id}" id="catButton${id}" aria-expanded="false" aria-controls="catBody=${id}">
      ${nome}
    </button>
  </h2>
  <div id="catBody${id}" class="accordion-collapse collapse" aria-labelledby="catHeader${id}">
    <div class="accordion-body">
      <div class="d-flex justify-content-between align-items-center mb-3 border-bottom pb-2">
        <div class="col-lg-auto">
          <span class="text-muted">Tópicos desta categoria</span>
          <button type="button" class="btn btn-sm btn-outline-primary ms-md-2" data-bs-toggle="modal" data-bs-target="#novoTop" data-bs-cat="${id}">
            + Adicionar Tópico
          </button>
        </div>
        <div class="col-lg-auto ms-auto">
          <button type="button" id="editarCat${id}" class="btn btn-sm btn-outline-secondary me-md-2" data-bs-toggle="modal" data-bs-target="#editarCat" data-bs-id="${id}" data-bs-nome="${nome}">
            ! Editar Categoria
          </button>
        </div>
        <div class="col-lg-auto">
          <button type="button" id="excluirCat${id}" class="btn btn-sm btn-outline-danger" data-bs-toggle="modal" data-bs-target="#excluirCat" data-bs-id="${id}" data-bs-nome="${nome}">
            - Excluir Categoria
          </button>
        </div>
      </div>
      <div class="row content-justify-center" id="catList${id}">
        <p class='text-start'>Ainda não há tópicos presentes</p>
      </div>
    </div>
  </div>
</div>`

    const accordion = document.getElementById('catAccordion');
    const semCat = accordion.querySelector('#semCat');

    if (semCat != null)
        semCat.remove();

    accordion.innerHTML += catStr;
}

function exibirNovoTopicoEmCategoria(topData) {
    const topStr = `<div class="col-4" id="top${topData.id}>
  <button type="button" class="btn" data-bs-toggle="modal" data-bs-target="#editarTop" data-bs-id="${topData.id}">
    <img src="${topData.imagem}" class="img-fluid img-thumbnail">
    <p>${topData.nome}</p>
  </button>
</div>`

    const list = document.getElementById(`catList${topData.categoria}`);
    const semTop = list.querySelector(`semTop${topData.categoria}`)

    if (semTop != null)
        semTop.remove();

    list.innerHTML += topStr;
}


export function carregarTopico() {

}

export function criarCategoria() {
    const nome = novaCatModal.querySelector('#novaCatNome').value;

    axios.post('/interno/categorias', {
        "nome": nome
    }).then(function (response) {
        bootstrap.Modal.getInstance(novaCatModal).hide();
        exibirNovaCategoria(response.data);

        const novaOpcao = `<option id="novoTopOpcao${response.data.id}" value="${response.data.id}">${response.data.nome}</option>
`;
        document.getElementById('novoTopCat').innerHTML += novaOpcao;
        document.getElementById('editarTopCat').innerHTML += novaOpcao;
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
        bootstrap.Modal.getInstance(editarCatModal).hide();
        const novoNome = response.data.nome;

        document.getElementById(`catButton${id}`).textContent = novoNome;
        document.getElementById(`editarCat${id}`).setAttribute('data-bs-nome', novoNome);
        document.getElementById(`excluirCat${id}`).setAttribute('data-bs-nome', novoNome);
        document.getElementById(`novoTopOpcao${id}`).textContent = novoNome;
        document.getElementById(`editarTopCatOpcao${id}`).textContent = novoNome;
    }).catch(function (error) {
        console.log(error);
    });
}

export function excluirCategoria() {
    const id = excluirCatModal.querySelector('#excluirCatId').value;

    axios.delete(`/interno/categorias/${id}`).then(function () {
        bootstrap.Modal.getInstance(excluirCatModal).hide();

        document.getElementById(`cat${id}`).remove();
        document.getElementById(`novoTopOpcao${id}`).remove();
        document.getElementById(`editarTopCatOpcao${id}`).remove();
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
        bootstrap.Modal.getInstance(editarTopModal).hide();

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

export function excluirTopico() {
    const id = excluirTopModal.querySelector('#excluirTopId').value;

    axios.delete(`interno/topicos/${id}`).then(function () {
        bootstrap.Modal.getInstance(excluirTopModal).hide();
        let div = document.getElementById(`top${id}`);

        if (div != null)
            div.remove();
    }).catch(function (error) {
        console.log(error);
    });
}


exibirTopicosPorCategoria();

exibirOpcoesCategoria();