window.addEventListener('beforeunload', function (e) {
    // Попытка отменить выгрузку стандартным способом
    e.preventDefault();
    // Установка строки запроса пользователю
    e.returnValue = 'Вы уверены в том что хотите покинуть страницу?';
    // fetch('/quests.jsp', {
    //     method: 'GET'
    // }).catch(error => console.error('Ошибка:', error));
});

//----------------------------------------
// let isFormChanged = false;
//
// document.querySelector("button_answer").addEventListener("change", function() {
//     isFormChanged = true;
// });
// window.onbeforeunload = function() {
//     if (!isFormChanged) {
//         return "Вы уверены, что хотите покинуть страницу? Все изменения будут потеряны!";
//     }
// };

//---------------------------------------
// const nameForm = document.querySelector("#answer");
//
// nameForm.addEventListener("input", (event) => {
//     if(event.target.value === ""){
//         fetch('/quests.jsp', {
//             method: 'GET'
//         }).catch(error => console.error('Ошибка:', error));
//     }
// });

//--------------------------------------------------------------
// const beforeUnloadHandler = (event) => {
//     fetch('http://localhost:8080/quests.jsp', {
//         method: 'GET'
//     }).then(response => {
//         if (response.ok) {
//             console.log('Resource deleted successfully');
//         } else {
//             console.log('Failed to delete resource');
//         }
//     }).catch(error => console.error('Ошибка:', error));
// };

// const radios = document.querySelectorAll('input[type="radio"]');
// radioInputs.forEach((radio) => {
//     radio.addEventListener("change", (event) => {
//         if (event.target.checked) {
//             window.addEventListener("beforeunload", beforeUnloadHandler);
//         } else {
//             window.removeEventListener("beforeunload", beforeUnloadHandler);
//         }
//     });
// });

//----------------------
// const beforeUnloadHandler = (event) => {
//     // Recommended
//     event.preventDefault();
//
//     // Included for legacy support, e.g. Chrome/Edge < 119
//     event.returnValue = true;
// };

// window.addEventListener('load', () => {
//     const radios = document.querySelectorAll('input[type="radio"]');
//     radios.forEach(radio => radio.checked = false);
// });

//---------------------------------------
// window.addEventListener("beforeunload", () => {
//     // Очистить радиокнопки перед уходом
//     const radios = document.querySelectorAll('input[type="radio"]');
//     radios.forEach(radio => radio.checked = false);
//     fetch('http://localhost:8080/quests.jsp', {
//         method: 'GET'
//     }).catch(error => console.error('Ошибка:', error));
// });
