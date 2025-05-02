import React,{useState} from 'react'
import { createMovie } from '../services/MovieService'
import {useNavigate} from 'react-router-dom';

const MovieComponent = () => {

const [title, setTitle] = useState('')
const [photoUrl, setPhotoUrl] = useState('')
const [content, setContent] = useState('')
const [duration, setDuration] = useState('')
const [cost, setCost] = useState('')

const [errors, setErrors] =useState({
    title: '',
    photoUrl: '',
    content: '',
    duration: '',
    cost: ''
})

const navigator = useNavigate();

function saveMovie(e){
    e.preventDefault();
    if(validateForm()){
        const movie = {title, photoUrl, content, duration, cost}
        console.log(movie)
        createMovie(movie).then((response) =>{
            console.log(response.data);
            navigator('/movies')
        })
    }
     
}

function validateForm(){
    let valid = true;

    const errorsCopy = {... errors}

    if(title.trim()){
        errorsCopy.title = '';
    } else{
        errorsCopy.title = 'Название фильма не должно быть пустым';
        valid = false;
    }
 
    if(photoUrl.trim()){
        errorsCopy.photoUrl = '';
    } else{
        errorsCopy.photoUrl = 'Ссылка на фото не должна быть пустой';
        valid = false;
    }

    if(content.trim()){
        errorsCopy.content = '';
    } else{
        errorsCopy.content = 'Описание не должно быть пустым';
        valid = false;
    }

    
    if(duration > 0 ){
        errorsCopy.duration = '';
    } else{
        errorsCopy.duration = 'Продолжительность не должна быть меньше нуля';
        valid = false;
    }

    if(cost > 0){
        errorsCopy.cost = '';
    } else{
        errorsCopy.cost = 'Цена не должна быть меньше нуля';
        valid = false;
    }
    setErrors(errorsCopy);
    return valid;
}

  return (
    <section class="py-5">
    <div class="container">
    <form role="form" method="post">
        <div class="container">
            <div class="form-row col-md-6 offset-md-3">
                <h3>Заполните информацию о фильме</h3>
                <div class="form-group">
                    <label for="title">Название фильма</label>
                    <input
                            type="text"
                            class="form-control"
                            id="title"
                            name="title"
                            className={` form-control ${ errors.title ? 'is-invalid' : '' }`}
                            value={title}
                            placeholder="Название"
                            onChange={(e) => setTitle(e.target.value)}
                            />
                            {errors.title && <div className='invalid-feedback'>{errors.title}</div>}
                   </div>
                <div class="form-group mt-2">
                    <label for="photoUrl">Ссылка на фото</label>
                    <input
                            type="text"
                            class="form-control"
                            id="photoUrl"
                            name="photoUrl"
                            className={` form-control ${ errors.photoUrl ? 'is-invalid' : '' }`}
                            placeholder="Photo Url"
                            onChange={(e) => setPhotoUrl(e.target.value)}/>
                    {errors.photoUrl && <div className='invalid-feedback'>{errors.photoUrl}</div>}
                </div>
                <div class="form-group mt-2">
                    <label for="content">Описание</label>
                    <input
                            type="text"
                            class="form-control"
                            id="content"
                            name="content"
                            className={` form-control ${ errors.content ? 'is-invalid' : '' }`}
                            placeholder="Описание"
                            onChange={(e) => setContent(e.target.value)}/>
                    {errors.content && <div className='invalid-feedback'>{errors.content}</div>}
                <div class="form-group mt-2">
                    <label for="duration">Продолжительность фильма</label>
                    <input
                            type="number"
                            class="form-control"
                            id="duration"
                            name="duration"
                            className={` form-control ${ errors.duration ? 'is-invalid' : '' }`}
                            onChange={(e) => setDuration(e.target.value)}
                            placeholder="Продолжительность (мин)"/>
                   {errors.duration && <div className='invalid-feedback'>{errors.duration}</div>}
                </div>
                
                <div class="form-group mt-2">
                    <label for="cost">Цена билета</label>
                    <input
                            type="number"
                            class="form-control"
                            id="cost"
                            name="cost"
                            className={` form-control ${ errors.cost ? 'is-invalid' : '' }`}
                            onChange={(e) => setCost(e.target.value)}
                            placeholder="Цена"/>
                    {errors.cost && <div className='invalid-feedback'>{errors.cost}</div>}
                </div>
            </div>
            <button type="submit" onClick={saveMovie} class="btn btn-dark col-4 offset-md-4 mt-4">Добавить фильм</button>
        </div>
        </div>
    </form>
    </div>
</section>
  )
}

export default MovieComponent