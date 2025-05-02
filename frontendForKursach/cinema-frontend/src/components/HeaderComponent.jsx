import React from 'react'
import { useNavigate } from 'react-router-dom'

const HeaderComponent = () => {

    const navigator = useNavigate();

    function addNewMovie(){
        navigator('/add-movie')
    }


  return (
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container px-4">
        <a class="navbar-brand" >Bileti.by</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                    <form  class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                        <div class="input-group">
                            <input name="query" type="text" class="form-control bg-light border-dark border-1 small" placeholder="Поиск мероприятий..."
                                   aria-label="Search" aria-describedby="basic-addon2"/>
                            <button class="btn btn-outline-dark" type="submit">Найти</button>
                        </div>
                    </form>
            </ul>
            <form class="d-flex me-2" >
                <a  class="btn btn-outline-dark" type="submit">
                    Войти
                </a>
                <a  class="btn btn-outline-dark" type="submit" >
                    Зарегестрироваться
                </a>
                <a  class="btn btn-outline-dark" type="submit">
                    <i class="bi-cart-fill me-1" ></i>
                    Корзина
                </a>
                <a class="btn btn-outline-dark" type="submit">
                    Пользователи
                </a>
            </form>
            
            <div  class="dropdown me-1">
                <a class="btn border-dark btn-secondary dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    Профиль
                </a>

                <ul  class="dropdown-menu">
                    <li ><a class="dropdown-item" >Билеты</a></li>
                    <li ><a class="dropdown-item" onClick={addNewMovie} >Добавить фильм</a></li>
                    <li ><a class="dropdown-item">Профиль</a></li>
                    <li><a class="dropdown-item" >Выйти</a></li>
                </ul>
            </div>

        </div>
    </div>
    
</nav>
  )
}

export default HeaderComponent