import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link, useParams } from "react-router-dom";

export default function Home() {

  const [music, setMusic] = useState([])

  const {id}=useParams()

  useEffect(() => {
    loadMusic();
  },[]);

  const loadMusic = async () => {
    const result = await axios.get("http://localhost:8080/api/favoritemusic")
    setMusic(result.data);
  }

  const deleteMusic=async (id)=>{
    await axios.delete(`http://localhost:8080/api/favoritemusic/${id}`)
    loadMusic()
  }

  return (
    <div className='container'>
        <div className='py-4'>
        <table className="table border shadow">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Name</th>
      <th scope="col">Artist</th>
      <th scope="col">Year</th>
      <th scope="col">Format</th>
      <th scope="col">Action</th>
    </tr>
  </thead>
  <tbody>
    {
        music.map((music, index)=>(
            <tr>
            <th scope="row" key={index}>{index+1}</th>
            <td>{music.name}</td>
            <td>{music.artist}</td>
            <td>{music.year}</td>
            <td>{music.format}</td>
            <td>
                <Link className="btn btn-primary mx-2" to={`/editmusic/${music.id}`}>Edit</Link>
                <button className="btn btn-danger mx-2" onClick={()=>deleteMusic(music.id)}>Delete</button>
            </td>
          </tr>
        ))
    }
  </tbody>
</table>
        </div>
    </div>
  )
}
