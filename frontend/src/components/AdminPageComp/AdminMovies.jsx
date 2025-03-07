import React, { useEffect, useState } from 'react'
import AdminMovie from './AdminMovie'
import AddMovieForm from './Forms/AddMovieForm'
import axios from "axios";
const AdminMovies = ({addMovieForm}) => {
  const [movies, setMovies] = useState([]);

  async function fetchMovies() {
    /* const apiUrl = import.meta.env.VITE_REACT_APP_API_URL;
    console.log(apiUrl);
    const {data} = await axios.get(`${apiUrl}/your-endpoint`); */
    const {data} = await axios.get("https://car-rental-api.up.railway.app/car");
    const movies = data.data;
    console.log(movies);
    setMovies(movies);
  }

  useEffect(() =>{
    fetchMovies();
  },[]);

  return (
      <div className="admin__movies__container">
            <div className="admin__movies">
              {movies.length > 0 && movies.map((movie, index) => <AdminMovie movie={movie} key={index} /> )}     
        </div>
        {addMovieForm && <AddMovieForm />}
    </div>

  )
}

export default AdminMovies
