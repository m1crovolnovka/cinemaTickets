import React, {useEffect, useState} from 'react'
import 'bootstrap/dist/css/bootstrap.min.css'
import { listMovies } from '../services/MovieService'

const ListMovieComponent = () => {

    const [movies, setMovies] = useState([])

    useEffect(() => {
        listMovies().then((response) => {
            setMovies(response.data);
        }).catch(error => {
            console.error(error);
        })
    }, [])

  return (
<main>
<div  class="bg-dark py-5">
  <div class="container px-4 px-lg-5 my-5">
    <div class="text-center text-white">
      <h1 class="display-4 fw-bolder">Заказ билетов!</h1>
      <p class="lead fw-normal text-white-50 mb-0">Покупай билеты на боевики, драммы, комедии, триллеры и многое другое </p>
    </div>
  </div>
</div>


<section class="py-5">
  {/* <div th:if="${param.success}" class="alert alert-success">
    Вы зарегестрировались успешно!
  </div> */}

  <div class="container px-4 px-lg-5 mt-5">
    <div>
      {/* <h1 th:if="${!movies.isEmpty}">Выбор пользователей</h1>
      <h1 th:if="${movies.isEmpty}">Мероприятий пока нет. Произошли технические неполадки</h1> */}
      <p></p>
    </div>
    <div  class="row gx-4 gx-lg-5 row-cols-3 row-cols-lg-4 row-cols-xxl-4 row-cols-sm-3 row-cols-md-2 row-cols-xl-4 justify-content-center">

{ 
movies.map(movie =>
      <div class="col mb-5">
        <div class="card h-100">
          <img height="400" class="card-img-top" src={movie.photoUrl} alt="..." />
          <div class="card-body p-4">
            <div class="text-center">
              <h5  class="fw-bolder">{movie.title}</h5>


              <div >
                <span>{movie.cost}</span>
                $
              </div>
              <div >{movie.place}</div>

            </div>
          </div>

          <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
            <div class="text-center">
              <a class="btn btn-outline-dark mt-auto" /*th:href="@{/movies/{movieId}(movieId=${movie.id})}"*/>Подробнее</a>
              <div /*sec:authorize="hasAuthority('ADMIN')" style="margin-top: 5px"*/>
                <a class="btn btn-outline-dark mt-auto" /*th:href="@{/movies/{movieId}/edit(movieId=${movie.id})}"*/>Изменить</a>
              </div>
            </div>

          </div>
        </div>
      </div>
)}
    </div>
  </div>
</section>
</main>
  )
}

export default ListMovieComponent