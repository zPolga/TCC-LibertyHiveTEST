document.addEventListener('DOMContentLoaded', async function () {
    // Nome do usuário salvo no localStorage
    const nome = localStorage.getItem('userNome');
    document.getElementById('usuarioNome').textContent = nome || "visitante";

    const lista = document.getElementById('listaProdutos');
    lista.innerHTML = "<p class='text-muted'>Carregando postagens...</p>";

    try {
        const token = localStorage.getItem("token");

        if (!token) {
            lista.innerHTML = "<p class='text-danger'>Você precisa estar logado para ver as postagens.</p>";
            return;
        }

        const response = await fetch("http://192.168.100.88:8080/api/postagens/listar-todas", {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        if (!response.ok) {
            lista.innerHTML = "<p class='text-danger'>Erro ao carregar postagens.</p>";
            return;
        }

        const postagens = await response.json();
        lista.innerHTML = "";

        if (postagens.length === 0) {
            lista.innerHTML = "<p class='text-muted'>Nenhuma postagem encontrada.</p>";
            return;
        }

        postagens.forEach(p => {
            const card = document.createElement('div');
            card.className = 'product-card';

            // Converter imagem principal em Base64 (se existir)
            let imgSrc = "./imagens/placeholder.png"; // imagem padrão
            if (p.imagem) {
                imgSrc = `data:image/jpeg;base64,${p.imagem}`;
            }
            let doacao_string = null;
            if (p.doacao) {
                doacao_string = "Sim";
            }else{
                doacao_string = "Não";
            }

            //alert(p.isProdOuServico);

            let tipo_string = null;
            if (!p.isProdOuServico) {
                tipo_string = "Produto";
            }else{
                tipo_string = "Serviço";
            }

            card.innerHTML = `
                <img src="${imgSrc}" alt="${p.nomePostagem}">
                <h3>${p.nomePostagem}</h3>
                <p><strong>Categoria:</strong> ${p.categoria}</p>
                <p><strong>Tipo:</strong> ${tipo_string}</p>
                <p><strong>Doação:</strong> ${doacao_string}</p>
                <small>${p.cidade} - ${p.uf}</small>
            `;

            lista.appendChild(card);
        });

    } catch (error) {
        console.error("Erro ao carregar postagens:", error);
        lista.innerHTML = "<p class='text-danger'>Erro de conexão com o servidor.</p>";
    }
});

function verMeusItens() {
    window.location.href = 'meusItens.html';
}

function logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('userNome');
    window.location.href = 'login.html';
}

function filtrarProdutos() {
    const filtro = document.getElementById('search').value.toLowerCase();
    const cards = document.querySelectorAll('.product-card');
    cards.forEach(card => {
        const nome = card.querySelector('h3').textContent.toLowerCase();
        const categoria = card.querySelector('p').textContent.toLowerCase();
        card.style.display = (nome.includes(filtro) || categoria.includes(filtro)) ? '' : 'none';
    });
}
