import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Header from '../../components/Header';
import {
    Button, IconButton, Snackbar, Alert, Dialog,
    DialogActions, DialogContent, DialogContentText, DialogTitle
} from '@mui/material';
import { DataGrid } from '@mui/x-data-grid';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import { useNavigate } from 'react-router-dom';

const VizualizarAluno = () => {
    const [alunos, setAlunos] = useState([]);
    const [snackbarOpen, setSnackbarOpen] = useState(false);
    const [snackbarMessage, setSnackbarMessage] = useState('');
    const [snackbarSeverity, setSnackbarSeverity] = useState('success');
    const [deleteDialogOpen, setDeleteDialogOpen] = useState(false);
    const [alunoToDelete, setAlunoToDelete] = useState(null);
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

    const handleDeleteClick = (id) => {
        setAlunoToDelete(id);
        setDeleteDialogOpen(true);
    };

    const confirmDelete = async () => {
        try {
            await axios.delete(`http://localhost:8080/aluno/${alunoToDelete}`);
            setAlunos(alunos.filter((aluno) => aluno.id !== alunoToDelete));
            setSnackbarMessage('Aluno deletado com sucesso!');
            setSnackbarSeverity('success');
        } catch (error) {
            console.error('Erro ao deletar aluno:', error);
            setSnackbarMessage('Erro ao deletar o aluno.');
            setSnackbarSeverity('error');
        }
        setSnackbarOpen(true);
        setDeleteDialogOpen(false);
        setAlunoToDelete(null);
    };

    const handleSnackbarClose = () => {
        setSnackbarOpen(false);
    };

    const handleDialogClose = () => {
        setDeleteDialogOpen(false);
        setAlunoToDelete(null);
    };

    const handleEdit = (id) => {
        console.log(`Edit student with id: ${id}`);
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
        {
            field: 'instituicaoEnsino',
            headerName: 'Instituição de Ensino',
            width: 150,
            valueGetter: (params) => params.row?.instituicaoEnsino?.id || ''
        },
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
                    <IconButton color="secondary" onClick={() => handleDeleteClick(params.row.id)}>
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

            <Snackbar open={snackbarOpen} autoHideDuration={6000} onClose={handleSnackbarClose}>
                <Alert onClose={handleSnackbarClose} severity={snackbarSeverity} sx={{ width: '100%' }}>
                    {snackbarMessage}
                </Alert>
            </Snackbar>

            <Dialog open={deleteDialogOpen} onClose={handleDialogClose}>
                <DialogTitle>Confirmar Exclusão</DialogTitle>
                <DialogContent>
                    <DialogContentText>
                        Tem certeza que deseja deletar este aluno?
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleDialogClose} color="primary">
                        Cancelar
                    </Button>
                    <Button onClick={confirmDelete} color="secondary" autoFocus>
                        Deletar
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    );
};

export default VizualizarAluno;
