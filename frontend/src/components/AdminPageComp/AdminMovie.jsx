const AdminMovie = ({movie}) => {
  return (
      <div className="admin__movie">
              <div className="admin__movie__header">
                <h3 className="admin__movie__title">{movie.model}</h3>
                <button className="admin__movie__edit__button">Edit</button>
              </div>
              <div className="admin__movie__rows">
                <div className="admin__movie__row">
                  <div className="admin__movie__info">
                    <span>Genre</span>
                    <span>{movie.genre}</span>
                  </div>
                  <div className="admin__movie__info">
                    <span>Rating</span>
                    <span>{movie.rating}</span>
                  </div>
                  <div className="admin__movie__info">
                    <span>Category</span>
                    <span>{movie.category}</span>
                  </div>
                  <div className="admin__movie__info">
                    <span>Image-URL</span>
                    <span>{movie.imag}</span>
                  </div>
                </div>
                <div className="admin__movie__row">
                  <div className="admin__movie__info">
                    <span>Cast</span>
                    <span>{movie.cast}</span>
                  </div>
                  <div className="admin__movie__info">
                    <span>Director</span>
                    <span>{movie.director}</span>
                  </div>
                  <div className="admin__movie__info">
                    <span>Producer</span>
                    <span>{movie.producer}</span>
                  </div>
                  <div className="admin__movie__info">
                    <span>Trailer-URL</span>
                    <span>{movie.trailer}</span>
                  </div>
                </div>
                <div className="admin__movie__row">
                  <div className="admin__movie__info">
                    <span>Date</span>
                    <span>{movie.date}</span>
                  </div>
                  <div className="admin__movie__info">
                    <span>Time</span>
                    <span>{movie.time}</span>
                  </div>
                  <div className="admin__movie__info">
                    <span>Sypnopsis</span>
                    <span>{movie.sypnosis}</span>
                  </div>
                </div>
                <i className="material-symbols-outlined admin__movie__trash">delete</i>
              </div>
            </div>
  )
}

export default AdminMovie
