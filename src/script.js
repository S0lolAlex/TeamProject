document.body.onclick = (event) =>{
    const elem = event.target;
        if(elem.classList.contains('section-symbol')){
            navigator.clipboard.writeText(elem.innerHTML)
                .then(() => {
                    document.querySelector('.out').innerHTML = 'Скопировано<br>'
                    })
        }
}