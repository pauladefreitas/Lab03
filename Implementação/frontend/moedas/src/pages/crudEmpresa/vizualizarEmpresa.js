import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Header from '../../components/Header';
import { Button, IconButton } from '@mui/material';
import { DataGrid } from '@mui/x-data-grid';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import { useNavigate } from 'react-router-dom';

const VizualizarEmpresa = () => {
    const navigate = useNavigate();

    const columns = [
        { field: 'id', headerName: 'ID', width: 10 },
        { field: 'nome', headerName: 'Nome da Empresa', width: 200 },
        {
            field: 'actions',
            headerName: 'Ações',
            width: 120,
            sortable: false,
            renderCell: (params) => (
                <>
                    <IconButton color="primary">
                        <EditIcon />
                    </IconButton>
                    <IconButton color="secondary" >
                        <DeleteIcon />
                    </IconButton>
                </>
            )
        }
    ];

    const cadEmpresa = () => {
        navigate('/cadastrarEmpresa')
    }


    return (
        <div>
            <Header />

            <div className='listEstudante'>
                <h1>Lista Empresas Parceiras</h1>
                <Button
                    onClick={cadEmpresa}
                    variant="contained"
                    sx={{ background: '#191970' }}
                    style={{ width: '300px' }}
                >
                    Adicionar nova empresa
                </Button>
            </div>

            <hr className='divider' />

            <div className="tableContainer">
                <div style={{ height: 400, width: '90%' }}>
                    <DataGrid
                        columns={columns}
                        pageSize={5}
                        rowsPerPageOptions={[5]}
                        disableSelectionOnClick
                        hideFooterSelectedRowCount
                        sx={{
                            '& .MuiDataGrid-columnHeaders': {
                                backgroundColor: '#f5f5f5',
                                color: '#333',
                            },
                            '& .MuiDataGrid-cell': {
                                backgroundColor: '#fff',
                                color: '#333',
                            },
                        }}
                    />  
                </div>
            </div>
        </div>
    );
};

export default VizualizarEmpresa;
