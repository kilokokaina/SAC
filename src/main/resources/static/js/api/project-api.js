async function chooseDirectory() {
    fetch('api/project/open_dialog').then(async response => {
        let dir = await response.text()
        document.querySelector('#projectDirectory').innerText = dir;
        console.log(dir);
    });
}

function createProject() {
    let projectName = document.querySelector('#projectName').value;
    let projectDescription = document.querySelector('#projectDescription').value;
    let projectDirectory = document.querySelector('#projectDirectory').innerText;

    let project = {
        projectName, projectDescription, projectDirectory
    }

    fetch('/api/project', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(project)
    }).then(async response => {
        console.log(await response.json());
    })
}