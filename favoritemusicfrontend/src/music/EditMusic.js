import axios from "axios";
import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from "react-router-dom";

export default function EditMusic() {

  let navigate=useNavigate()

  const {id}=useParams()

  const [music, setMusic]=useState({
    name:"",
    artist:"",
    year:"",
    format:""
  })

  const{name,artist,year,format}=music

  const onInputChange=(e)=>{
    setMusic({...music, [e.target.name]:e.target.value})
  }

  useEffect(()=>{
    loadMusic()
  },[]);

  const onSubmit=async (e)=>{
    e.preventDefault();
    await axios.put(`http://localhost:8080/api/favoritemusic/${id}`, music)
    navigate("/")
  }

  const loadMusic=async ()=>{
    const result=await axios.get(`http://localhost:8080/api/favoritemusic/${id}`)
    setMusic(result.data)
  }

  return (
    <div className="container">
        <div className="row">
            <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                <h2 className="text-center m-4">Edit Music</h2>
                <form onSubmit={(e)=>onSubmit(e)}>
                <div className="mb-3">
                    <label htmlFor="Name" className="form-label">
                        Name
                    </label>
                    <input required type={"text"} className="form-control" placeholder="Enter Music Name" name="name" value={name} onChange={(e)=>onInputChange(e)}/>
                </div>
                <div className="mb-3">
                    <label htmlFor="Artist" className="form-label">
                    Artist
                    </label>
                    <input required type={"text"} className="form-control" placeholder="Enter Artist" name="artist" value={artist} onChange={(e)=>onInputChange(e)}/>
                </div>
                <div className="mb-3">
                    <label htmlFor="Year" className="form-label">
                    Year
                    </label>
                    <input required type={"number"} className="form-control" placeholder="Enter Year" name="year" value={year} onChange={(e)=>onInputChange(e)}/>
                </div>
                <div className="mb-3">
                    <label htmlFor="Format" className="form-label">
                    Format
                    </label>
                    <input required type={"text"} className="form-control" placeholder="Enter Format" name="format" value={format} onChange={(e)=>onInputChange(e)}/>
                </div>
                <button type="submit" className="btn btn-outline-primary">Submit</button>
                <Link className="btn btn-outline-danger mx-2" to="/">Cancel</Link>
                </form>
            </div>
        </div>
    </div>
  )
}
