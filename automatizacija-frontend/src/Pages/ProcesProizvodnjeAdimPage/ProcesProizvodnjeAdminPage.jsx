import React, { useEffect, useState } from 'react';
import { useTable } from 'react-table';
import axiosHttp from '../../Components/utils/axiosHttp';
import '../ProcesProizvodnjeKorisnikPage/ProcesProizvodnjeKorisnikPage.css';
import {ToastContainer, toast} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function ProcesProizvodnjeAdminPage() {

    const [procesProizvodnjeAdmin, setProcesProizvodnjeAdmin] = useState([]);

    async function fetchData() {
        try{
            await axiosHttp.get('http://localhost:8080/api/procesi-proizvodnje')
            .then(res => {
                setProcesProizvodnjeAdmin(res.data);
                console.log(res.data);
            })
        }catch(err) {
            console.log('neuspesno');
        }
    }
    
    useEffect(() => {
        fetchData();
    }, []);

    const data = React.useMemo(() => procesProizvodnjeAdmin, [procesProizvodnjeAdmin]);
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
            Header: "Serijski Broj",
            accessor: "proizvodnja.masina.serijskiBroj",
        },
        {
            Header: "Sok",
            accessor: "proizvodnja.sok.vrsta",
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

    const handleClick = async (proces_proizvodnje_id) => {
        console.log(proces_proizvodnje_id);
        try {
            await axiosHttp.get(`http://localhost:8080/api/proces-proizvodnje/zavrsi/${proces_proizvodnje_id}`)
                .then(res => {
                    toast.success("Uspesno ste potvrdili zavrsetak procesa proizvodnje");
                    setTimeout(() => {
                        setProcesProizvodnjeAdmin(prevState => prevState.map(proces => proces.proces_proizvodnje_id === res.data.proces_proizvodnje_id ? res.data : proces));
                    }, 1000);
                    
                });
        } catch(err) {
            toast.error("Proces nije uspesno zavrsen");
        }
    }

  return (
    <div>
        <ToastContainer position='top-center'/>
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
                                <th>Akcija</th>
                            </tr>
                        ))}
                    </thead>
                    <tbody {...getTableBodyProps()}>
                        {rows.map((row) => {
                            prepareRow(row)
                            return (
                            <tr {...row.getRowProps()}>
                                {row.cells.map((cell) => (
                                    <td {...cell.getCellProps()}  style={{ whiteSpace: 'normal' }}>{cell.render("Cell")}</td>
                                ))}
                                <td>
                                    {
                                     row.original.aktivna === true ?
                                     <button className="zavrsi" onClick={() => handleClick(row.original.proces_proizvodnje_id)}>Zavrsi</button> : 
                                     <span className="text-danger">Zavrsena akcija</span>
                                    }
                                </td>
                            </tr> 
                            )
                        })}
                    </tbody>
                </table>
            </div>
        </div>
    </div>
  )
}

export default ProcesProizvodnjeAdminPage