
function showAlert(event) {
    const wrapper = $(document.body).children('.wrapper');
    wrapper.toggleClass('sidebar-inactive-l');
    event.preventDefault();
}