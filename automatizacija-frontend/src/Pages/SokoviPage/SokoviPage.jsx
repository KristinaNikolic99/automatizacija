import React, {useState, useEffect}  from 'react';
import { useNavigate} from 'react-router-dom';
import '../SokoviPage/SokoviPage.css';
import axiosHttp from '../../Components/utils/axiosHttp.jsx';


function SokoviPage() {

  const [sokovi, setSokovi] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    try{
      axiosHttp.get('http://localhost:8080/api/sokovi')
      .then(res => {
        setSokovi(res.data);
      })
    } catch(err) {
      console.log('neuspesno')
    }
  },[])

  return (
    <div className='all'>
      <div>
        <h1 style={{fontWeight:'bold', textDecoration:'underline'}}>Sokovi</h1>
        <section className='container'>
          {
            sokovi?.map((sok) => (
              <div className='card' key={sok.sok_id}>
                <div className='card-image'
                  style={{backgroundImage: `url(/images/${sok.slika})`}}>
                </div>
                <h2>{sok.vrsta}</h2>
                <p style={{textAlign: "justify"}}>
                  {sok.opis}
                </p>
                <a href='' onClick={(e) => {
                  e.preventDefault();
                  navigate(`/proizvodnja/${sok.sok_id}/${sok.vrsta}`);
                }}>PROIZVEDI</a>
              </div>
            ))
            
          }
          
        </section>
      </div>
    </div>
  )
}

export default SokoviPage
