import React, { useEffect, useState } from 'react';
import { useTable } from 'react-table';
import axiosHttp from '../../Components/utils/axiosHttp';
import '../ProcesProizvodnjeKorisnikPage/ProcesProizvodnjeKorisnikPage.css'


function ProcesProizvodnjeKorisnikPage() {
    const [procesProizvodnjeKorisnik, setProcesProizvodnjeKorisnik] = useState([]);

async function fetchData() {
    try{
        await axiosHttp.get('http://localhost:8080/api/proces-proizvodnje/korisnik')
        .then(res => {
            setProcesProizvodnjeKorisnik(res.data);
            console.log(res.data);
        })
    }catch(err) {
        console.log('neuspesno');
    }
}

useEffect(() => {
    fetchData();
}, []);

const data = React.useMemo(() => procesProizvodnjeKorisnik, [procesProizvodnjeKorisnik]);
const columns = React.useMemo(() => [
    {
        Header: "ID Procesa Proizvodnje",
        accessor: "proces_proizvodnje_id",
    },
    {
        Header: "Proces U Toku",
        accessor: "aktivna",
        Cell: ({ value }) => value ? 'Aktivan' : 'Neaktivan',
    },
    {
        Header: "Datum Pocetka",
        accessor: "datumPocetka",
        Cell: ({ value }) => {
            const date = new Date(value);
            return date.toLocaleDateString() + ' ' + date.toLocaleTimeString();
        }
    },
    {
        Header: "Masina",
        accessor: "proizvodnja.masina.naziv",
    },
    {
        Header: "Korisnik",
        accessor: row => `${row.korisnik.ime} ${row.korisnik.prezime}, ${row.korisnik.email}`,
    },
    
], []);

const {
    getTableProps,
    getTableBodyProps,
    headerGroups,
    rows,
    prepareRow
  } = useTable({
    columns,
    data,
  });



  if (data.length === 0 && sessionStorage.getItem("korisnik") === "Kupac") {
    return (
        <div className='background_p'>
            <div className='poruka'>
                <p>
                    Nema evidencije o pokrenutim procesima proizvodnje za ovog korisnika!
                </p>
                <p>
                    Molimo Vas prvo odaberite sok koji zelite da bude proizveden kako bi ste mogli videti detalje o pokrenutoj proizvodnji.
                </p>
            </div>
        </div>
    );
}


  return (
    <div className='main-vg'>
        <div className='container-vg'>
            <table {...getTableProps()}>
                <thead>
                    {headerGroups.map((headerGroup) => (
                        <tr {...headerGroup.getHeaderGroupProps()}>
                            {headerGroup.headers.map((column) => (
                                <th {...column.getHeaderProps()}>
                                    {column.render("Header")}
                                </th>
                            ))}
                        </tr>
                    ))}
                </thead>
                <tbody {...getTableBodyProps()}>
                    {rows.map((row) => {
                        prepareRow(row)
                        return (
                           <tr {...row.getRowProps()}>
                            {row.cells.map((cell) => (
                                <td {...cell.getCellProps()} style={{ whiteSpace: 'normal' }}>{cell.render("Cell")}</td>
                            ))}
                           </tr> 
                        )
                    })}
                </tbody>
            </table>
        </div>
    </div>
  )
}

export default ProcesProizvodnjeKorisnikPage