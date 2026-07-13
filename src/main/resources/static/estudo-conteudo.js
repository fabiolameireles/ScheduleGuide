import "./axios.min.js"

document.getElementById('btn-salvar-anotacoes').addEventListener('click', function() {
    const btn = this;

    const textarea = document.getElementById('anotacoes');
    const conteudoId = textarea.getAttribute('data-id');
    const textoAnotacoes = textarea.value;

    const textoSave = document.getElementById('texto-save')

    btn.disabled = true;

    axios.patch('/interno/conteudos/salvar-anotacoes', null, {
        params: {
            id: conteudoId,
            anotacoes: textoAnotacoes
        }
    })
    .then(function (response) {
        const dataAtual = new Date();
        const horas = String(dataAtual.getHours()).padStart(2, '0');
        const minutos = String(dataAtual.getMinutes()).padStart(2, '0');
        const segundos = String(dataAtual.getSeconds()).padStart(2, '0');

        textoSave.innerText = `Salvo às ${horas}:${minutos}:${segundos}`;
        textoSave.style.display = 'inline';
    })
    .catch(function (error) {
        console.log(error);

        textoSave.innerText = "Falha ao salvar"
        textoSave.style.display = 'inline'
    })
    .finally(function() {
        btn.disabled = false;
    });
});
