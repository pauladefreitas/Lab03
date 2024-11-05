import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Header from '../../components/Header';
import { Button, IconButton } from '@mui/material';
import { DataGrid } from '@mui/x-data-grid';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import './vizualizarAluno.css';
import { useNavigate } from 'react-router-dom';

const CadastrarAluno = () => {
    const [alunos, setAlunos] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchAlunos = async () => {
            try {
                const response = await axios.get('http://localhost:8080/aluno');
                setAlunos(response.data);
            } catch (error) {
                console.error('Erro ao carregar alunos:', error);
            }
        };
        
        fetchAlunos();
    }, []);

    const handleEdit = (id) => {
        console.log(`Edit student with id: ${id}`);
    };

    const handleDelete = async (id) => {
        if (window.confirm('Tem certeza que deseja deletar este aluno?')) {
            try {
                await axios.delete(`http://localhost:8080/aluno/${id}`);
                alert('Aluno deletado com sucesso!');
                setAlunos(alunos.filter((aluno) => aluno.id !== id));
            } catch (error) {
                console.error('Erro ao deletar aluno:', error);
                alert('Erro ao deletar o aluno.');
            }
        }
    };

    const columns = [
        { field: 'id', headerName: 'ID', width: 10 },
        { field: 'nome', headerName: 'Nome', width: 120 },
        { field: 'email', headerName: 'Email', width: 150 },
        { field: 'endereco', headerName: 'Endereço', width: 150 },
        { field: 'saldoMoedas', headerName: 'Moedas', width: 70 },
        { field: 'curso', headerName: 'Curso', width: 150 },
        { field: 'rg', headerName: 'RG', width: 100 },
        { field: 'cpf', headerName: 'CPF', width: 150 },
        { field: 'instituicaoEnsino', headerName: 'Instituição de Ensino', width: 150, valueGetter: (params) => params.row.instituicaoEnsino?.id },
        {
            field: 'actions',
            headerName: 'Ações',
            width: 120,
            sortable: false,
            renderCell: (params) => (
                <>
                    <IconButton color="primary" onClick={() => handleEdit(params.row.id)}>
                        <EditIcon />
                    </IconButton>
                    <IconButton color="secondary" onClick={() => handleDelete(params.row.id)}>
                        <DeleteIcon />
                    </IconButton>
                </>
            )
        }
    ];

    const cadAluno = () => {
        navigate('/cadastrarAluno')
    }

    return (
        <div>
            <Header />

            <div className='listEstudante'>
                <h1>Lista de Alunos</h1>
                <Button
                    onClick={cadAluno}
                    variant="contained"
                    sx={{ background: '#191970' }}
                    style={{ width: '300px' }}
                >
                    Adicionar novo estudante
                </Button>
            </div>

            <hr className='divider' />

            <div className="tableContainer">
                <div style={{ height: 400, width: '90%' }}>
                    <DataGrid
                        rows={alunos}
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

export default CadastrarAluno;
