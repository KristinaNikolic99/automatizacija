import React, {useEffect, useState} from 'react';
import '../ProizvodnjaPage/ProizvodnjaPage.css';
import {  useNavigate, useParams } from 'react-router-dom';
import axiosHttp from '../../Components/utils/axiosHttp.jsx';
import {ToastContainer, toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';


function ProizvodnjaPage() {

    const { id, vrsta } = useParams();
    const [proizvodnja, setProizvodnja] = useState([]);
    const navigate = useNavigate();

    async function fetchData() {
        try{
            await axiosHttp.get(`http://localhost:8080/api/proizvodnjaSok/${id}`)
            .then(res => {
              setProizvodnja(res.data);
            })
          } catch(err) {
            console.log('neuspesno')
          }
    }

    useEffect(() => {
        fetchData();
    },[]);

    const [formData, setFormData] = useState({
      masina_id: '',
      kolicina: '',
    });

    const handleSelectedChange = (e) => {
      const masina_id = e.target.value;
      setFormData({
        ...formData,
        masina_id
      });
    }

    const handleInputChange = (e) => {
      const kolicina = e.target.value;
      setFormData({
        ...formData,
        kolicina
      })
    }

    const handleSubmit = async (e) => {
      e.preventDefault();
      console.log("Forma je:", formData);
      await axiosHttp.post("http://localhost:8080/api/proces-proizvodnje", {
        kolicina: formData.kolicina,
        sok_id: id,
        masina_id: formData.masina_id
      })
      .then(res => {
        toast.success(res.data.poruka);
      })
      .catch(res => {
        toast.error(res.response.data.poruka);
      });
    }

    const handleClick = () => {
      setTimeout(() => {
        navigate('/procesProizvodnjeKorisnik');
      }, 5000);
    }

  return (
    <div className='proizvodnja'>
      <ToastContainer position='top-center'/>
        <div className='wrapper'>
            <form onSubmit={handleSubmit}>
                <h1>Započni proizvodnju: <br/> <span style={{color:'salmon'}}>{vrsta}</span></h1>
                <div className='input-box'>
                    <input type='number' id='kolicina' value={formData.kolicina} onChange={handleInputChange} placeholder='Unesite željenu količinu' min={1}/>
                </div>
                <div className='input-box'>
                    <select value={formData.masina_id} onChange={handleSelectedChange}>
                        <option value="" disabled>Izaberite masinu za pravljenje...</option>
                        {
                          proizvodnja.length !== 0 ? proizvodnja.map((item) => (
                            <option key={item.proizvodnja_id} value={item.masina.masina_id}>
                                {item.masina.naziv}
                            </option>)) : <option value="" disabled>Nijedna masina koja proizvodi ovaj sok trenutno nije slobodna</option>
                        }
                    </select>
                </div>
                <button type='submit' onClick={handleClick}>Proizvedi sok</button>
            </form>
        </div>
    </div>
  )
}

export default ProizvodnjaPage
