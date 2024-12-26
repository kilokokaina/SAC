function uploadData() {
    const files = document.querySelector('#papers-list').files;
    const filesList = document.querySelector('#files-list');
    let formData;
    let counter = 0;

    console.log(files);
    console.log(files.length);
    for (let i = 0; i < files.length; i++) {
        formData = new FormData();
        formData.append('file', files[i]);

        fetch('api/data/upload', {
            method: 'POST',
            body: formData
        }).then(async response => {
            let uploadStatus = document.querySelector(`#file-${i}`);
            if (response.status === 200) {
                uploadStatus.innerHTML = `<h6 id="file-${i}"><badge class="badge text-bg-success">Обработано</badge> ${files[i].name}</h6>`;
            } else {
                uploadStatus.innerHTML = `<h6 id="file-${i}"><badge class="badge text-bg-danger">Ошибка загрузки</badge> ${files[i].name}</h6>`;
            }

            if (++counter === files.length) {
                renderTable();
                await findReferences();
                await updateGraph();
            }
        });
    }

    filesList.innerHTML = '';
    for (let i = 0; i < files.length; i++) {
        filesList.innerHTML += `<h6 id="file-${i}"><span class="spinner-border text-primary me-1" role="status" aria-hidden="true"></span> ${files[i].name}</h6>`
    }
}

function updateList() {
    const files = document.querySelector('#papers-list').files;
    const filesList = document.querySelector('#files-list');

    filesList.innerHTML = '';
    for (let i = 0; i < files.length; i++) {
        filesList.innerHTML += `<h6 id="file-${i}"><badge class="badge text-bg-warning">Готов к загрузке</badge> ${files[i].name}</h6>`
    }
}

function renderTable() {
    let startRenderTime = Date.now();

    new DataTable('#data-list', {
        destroy: true,
        language: {
            url: '//cdn.datatables.net/plug-ins/2.0.8/i18n/ru.json'
        },
        ajax: 'api/data/get/datatable',
        processing: true,
        serverSide: true,
        columns: [
            {data: "documentValues.Authors"},
            {data: "documentValues.Author full names"},
            {data: "documentValues.Author(s) ID"},
            {data: "documentValues.Title"},
            {data: "documentValues.Year"},
            {data: "documentValues.Source title"},
            {data: "documentValues.Volume"},
            {data: "documentValues.Issue"},
            {data: "documentValues.Art\\. No\\."},
            {data: "documentValues.Page start"},
            {data: "documentValues.Page end"},
            {data: "documentValues.Page count"},
            {data: "documentValues.Cited by"},
            {data: "documentValues.DOI"},
            {data: "documentValues.Link"},
            {data: "documentValues.Affiliations"},
            {data: "documentValues.Authors with affiliations"},
            {data: "documentValues.Abstract"},
            {data: "documentValues.Author Keywords"},
            {data: "documentValues.Index Keywords"},
            {data: "documentValues.Molecular Sequence Numbers"},
            {data: "documentValues.Chemicals/CAS"},
            {data: "documentValues.Tradenames"},
            {data: "documentValues.Manufacturers"},
            {data: "documentValues.Funding Details"},
            {data: "documentValues.Funding Texts"},
            {data: "documentValues.References"},
            {data: "documentValues.Correspondence Address"},
            {data: "documentValues.Editors"},
            {data: "documentValues.Publisher"},
            {data: "documentValues.Sponsors"},
            {data: "documentValues.Conference name"},
            {data: "documentValues.Conference date"},
            {data: "documentValues.Conference location"},
            {data: "documentValues.Conference code"},
            {data: "documentValues.ISSN"},
            {data: "documentValues.ISBN"},
            {data: "documentValues.CODEN"},
            {data: "documentValues.PubMed ID"},
            {data: "documentValues.Language of Original Document"},
            {data: "documentValues.Abbreviated Source Title"},
            {data: "documentValues.Document Type"},
            {data: "documentValues.Publication Stage"},
            {data: "documentValues.Open Access"},
            {data: "documentValues.Source"},
            {data: "documentValues.EID"}
        ],
        responsive: true,
        scrollY: 600,
        scrollCollapse: true,
        ordering: false
    });

    let endRenderTime = Date.now();
    console.log('Render: ' + (endRenderTime - startRenderTime) + ' ms');
}

async function findReferences() {
    const startTime = Date.now();

    const response = await fetch("api/data/update/ref", { method: "GET" });
    const status = response.status;

    if (status === 200) console.log('References: OK. ' + (Date.now() - startTime) + ' ms');
}


async function updateGraph() {
    await updateNodes();
    await updateEdges();
}

async function updateNodes() {
    const startTime = Date.now();

    const response = await fetch("api/node/update", { method: "GET" });
    const status = response.status;

    if (status === 200) console.log('Nodes: OK. ' + (Date.now() - startTime) + ' ms');
}

async function updateEdges() {
    const startTime = Date.now();

    const response = await fetch("api/edge/update", { method: "GET" });
    const status = response.status;

    if (status === 200) console.log('Edges: OK. ' + (Date.now() - startTime) + ' ms');
}
