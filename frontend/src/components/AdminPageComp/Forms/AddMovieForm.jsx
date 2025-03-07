import React from 'react'
import { useState } from 'react';
import axios from "axios";
import SimpleAlert from "../../SimpleAlert";

const AddMovieForm = () => {
    const [selectedTimes, setSelectedTimes] = useState([]);
    const [formData, setFormData] = useState({
      name: "",
      genre: "",
      rating: "",
      selectedTimes: [],
      date: "",
      category: "",
      cast: "",
      director: "",
      producers: "",
      synopsis: "",
      imageURL: "",
      trailerURL: "",
    });
    const[showAlert, setShowAlert] = useState(false);

    const handleTimeChange = (event) => {
        const selectedOptions = Array.from(event.target.selectedOptions, (option) => option.value);
        setSelectedTimes(selectedOptions);
    };

    const handleSubmit = (event) => {
      event.preventDefault();
      setFormData({name: "", genre: "", rating: "", selectedTimes: [], date: "", category: "", cast: "", director: "", producers: "", synopsis: "", imageURL: "", trailerURL: "",});
      createMovie();
      handleAlert();
    };

    const handleChange = (event) => {
      const { name, value } = event.target;
      setFormData((prevData) => ({
          ...prevData,
          [name]: value,
      }));
    };

    async function createMovie() {
      await axios.post("https://611f89f2-320e-47c4-87f6-ba96954ccc48.mock.pstmn.io", formData);
    }

    const handleAlert = () =>{
      setShowAlert(true);
      setTimeout(() => setShowAlert(false), 3000);
    };


  return (
    <div className='admin__movie__form'>
      <h2 className="admin__add__movie__title">Add a new Movie</h2>
      <form onSubmit={handleSubmit} className='admin__add__movie__form'>
        <label htmlFor="name">Name:</label>
        <input type="text" name="name" placeholder="Enter the Title" value={formData.name} onChange={handleChange} required/>

        <label htmlFor="text">Genre:</label>
        <input type="text" name="genre" placeholder="Enter your email" value={formData.genre} onChange={handleChange} required/>

        <label htmlFor="rating">Rating:</label>
        <input type="text" name="rating" placeholder="Enter the rating" value={formData.rating} onChange={handleChange} required/>

        <label>Select Time Slots:</label>
        <select name="selectedTimes" multiple onChange={handleTimeChange}>
            {["11:00 AM - 12:30 PM", "12:00 PM - 1:30 PM", "01:00 PM - 2:30PM", "02:00 PM - 3:30PM", "03:00 PM - 4:30 PM ", "04:00 PM - 5:30 PM ", "05:00 PM - 6:30 PM ", "06:00 PM - 7:30 PM ", "07:00 PM - 8:30 PM ", "08:00 PM - 9:30 PM ", "09:00 PM - 10:30 PM ", "10:00 PM - 11:30 PM ", "11:00 PM - 12:30 AM "].map((time) => (
            <option key={time} value={time}>
                {time}
            </option>
            ))}
        </select>

      <p>Selected Times: <br/> {selectedTimes.join(", ")}</p>


        <label htmlFor="date">Date:</label>
        <input type='date' name="date" placeholder="Enter the Available Dates" value={formData.date} onChange={handleChange}></input>


        <label htmlFor="category">Category</label>
        <input type="text" name="category" placeholder="Enter the Category" value={formData.category} onChange={handleChange} ></input>


        <label htmlFor="cast">Cast:</label>
        <input type="text" name="cast" placeholder="Enter the Cast" value={formData.cast} onChange={handleChange} ></input>


        <label htmlFor="director">Director:</label>
        <input type="text" name="director" placeholder="Enter the Director" value={formData.director} onChange={handleChange} ></input>

        <label htmlFor="Producers">Producers(s):</label>
        <input type="text" name="producers" placeholder="Enter the Producers" value={formData.producers} onChange={handleChange} ></input>


        <label htmlFor="Synopsis">Synopsis:</label>
        <textarea name="synopsis" placeholder="Enter the Synopsis" value={formData.synopsis} onChange={handleChange} ></textarea>


        <label htmlFor="image">Image-URL:</label>
        <input type='url' name="imageURL" placeholder="Enter the Image URL" value={formData.imageURL} onChange={handleChange} ></input>

        <label htmlFor="trailer">Trailer-URL:</label>
        <input type='url' name="trailerURL" placeholder="Enter the Trailer-URL" value={formData.trailerURL} onChange={handleChange} ></input>
        
        <div className='admin__add__movie__form__button__container'>
         <button className='admin__add__movie__form__button' type="submit">Create Movie</button>
        </div>

        {showAlert && <SimpleAlert message="Movie Created Successfully!!!" />}
        
        </form>
    </div>
  )
}

export default AddMovieForm
