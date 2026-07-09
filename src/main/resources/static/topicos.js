import "./axios.min.js"

async function exibirTopicosPorCategoria() {
    var cats = `<div class="accordion" id="catAccordion">
`;
    var catData = await axios.get('/interno/categorias').then((catResponse) => catResponse.data).catch(function (catError) {
        console.log(catError);
    });

    for (var i = 0; i < catData.length; i++) {
        const catID = catData[i].id;
        const catNOME = catData[i].nome;

        cats += `  <div class="accordion-item">
    <h2 class="accordion-header" id="catHeader$${catID}">
      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#catBody${catID}" aria-expanded="true" aria-controls="catBody${catID}">
        {catNOME}
      </button>
    </h2>
    <div id="catBody${catID}" class="accordion-collapse collapse" aria-labelledby="catHeader${catID}">
      <div class="accordion-body">

    
    <div class="d-flex justify-content-between align-items-center mb-3 border-bottom pb-2">
          <span class="text-muted">Tópicos desta categoria</span>
          <button type="button" class="btn btn-sm btn-outline-primary" 
                  data-bs-toggle="modal" 
                  data-bs-target="#novoTop" 
                  data-bs-catid="${catID}">
            + Adicionar Tópico
          </button>
        </div>
`;
        var topData = await axios.get(`/interno/topicos/lista/${catData[i].id}`).then((topResponse) => topResponse.data).catch(function (topError) {
            console.log(topError);
        });

        for (var j = 0; j < topData.length; j++) {
            if (!(j%3))
                cats += `        <div class="row justify-content-center">`

            cats += `          <div class="col-4">
            <a href="/topicos/conteudos?topicoId=${topData[j].id}" class="btn">
              <img src="{topIMG}" class="img-thumbnail">
              <p>{topNOME}</p>
            </a>
          </div>
`
            cats = cats.replace(`{topIMG}`, topData[j].imagem).replace(`{topNOME}`, topData[j].nome);

            if ((j%3 == 2) || (j == topData.length-1))
                cats += `        </div>`
        }

        if (topData.length == 0)
            cats += "        <p class='text-start'>Ainda não há tópicos presentes</p>"

        cats = cats.replace(`{catNOME}`, catData[i].nome);
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

export function criarTopico() {
    let nome = document.getElementById('novoTopNome').value;
    let imagem = document.getElementById('novoTopImagem').value;

    let categoriaId = document.getElementById('novoTop').getAttribute('data-current-cat-id');

    axios.post('/interno/topicos', {
        "nome": nome,
        "imagem": imagem,
        "categoria": {"id": categoriaId}
    }).then(function (response) {
        exibirTopicosPorCategoria();
    }).catch(function (error) {
        console.log(error);
    });
}

// export function editarTopico() {}

exibirTopicosPorCategoria();