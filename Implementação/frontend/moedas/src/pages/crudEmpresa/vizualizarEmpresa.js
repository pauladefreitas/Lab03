import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Header from '../../components/Header';
import ModalAddVantagem from "../../components/modals/modalAddVantagem"
import {
    Button, IconButton, Snackbar, Alert, Dialog,
    DialogActions, DialogContent, DialogContentText, DialogTitle, TextField
} from '@mui/material';
import { DataGrid } from '@mui/x-data-grid';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import AddCircleIcon from '@mui/icons-material/AddCircle';
import { useNavigate } from 'react-router-dom';
import '../crudAluno/vizualizarAluno.css'

const VizualizarEmpresa = () => {
    const navigate = useNavigate();
    const [empresas, setEmpresas] = useState([]);
    const [snackbarOpen, setSnackbarOpen] = useState(false);
    const [snackbarMessage, setSnackbarMessage] = useState('');
    const [snackbarSeverity, setSnackbarSeverity] = useState('success');
    const [deleteDialogOpen, setDeleteDialogOpen] = useState(false);
    const [empresaToDelete, setEmpresaToDelete] = useState(null);
    const [openEditModal, setOpenEditModal] = useState(false);
    const [selectedEmpresa, setSelectedEmpresa] = useState(null);
    const [editedNome, setEditedNome] = useState('');
    const [openVantagemModal, setOpenVantagemModal] = useState(false);


    useEffect(() => {
        const fetchEmpresas = async () => {
            try {
                const response = await axios.get('http://localhost:8080/empresas_parceiras');
                setEmpresas(response.data);
            } catch (error) {
                console.error('Erro ao carregar empresas parceiras:', error);
            }
        };

        fetchEmpresas();
    }, []);

    const handleDeleteClick = (id) => {
        setEmpresaToDelete(id);
        setDeleteDialogOpen(true);
    };

    const confirmDelete = async () => {
        try {
            await axios.delete(`http://localhost:8080/empresas_parceiras/${empresaToDelete}`);
            setEmpresas(empresas.filter((empresa) => empresa.id !== empresaToDelete));
            setSnackbarMessage('Empresa deletada com sucesso!');
            setSnackbarSeverity('success');
        } catch (error) {
            console.error('Erro ao deletar empresa:', error);
            setSnackbarMessage('Erro ao deletar a empresa.');
            setSnackbarSeverity('error');
        }
        setSnackbarOpen(true);
        setDeleteDialogOpen(false);
        setEmpresaToDelete(null);
    };

    const handleEditClick = (empresa) => {
        setSelectedEmpresa(empresa);
        setEditedNome(empresa.nome);
        setOpenEditModal(true);
    };

    const handleSaveEdit = async () => {
        try {
            await axios.put(`http://localhost:8080/empresas_parceiras/${selectedEmpresa.id}`, { nome: editedNome });
            setEmpresas(empresas.map(emp => emp.id === selectedEmpresa.id ? { ...emp, nome: editedNome } : emp));
            setSnackbarMessage('Empresa editada com sucesso!');
            setSnackbarSeverity('success');
        } catch (error) {
            console.error('Erro ao editar empresa:', error);
            setSnackbarMessage('Erro ao editar a empresa.');
            setSnackbarSeverity('error');
        }
        setSnackbarOpen(true);
        handleEditClose();
    };

    const handleSnackbarClose = () => {
        setSnackbarOpen(false);
    };

    const handleDialogClose = () => {
        setDeleteDialogOpen(false);
        setEmpresaToDelete(null);
    };

    const handleEditClose = () => {
        setOpenEditModal(false);
        setSelectedEmpresa(null);
    };

    const columns = [
        { field: 'id', headerName: 'ID', width: 100 },
        { field: 'nome', headerName: 'Nome da Empresa', width: 200 },
        {
            field: 'actions',
            headerName: 'Ações',
            width: 150,
            sortable: false,
            renderCell: (params) => (
                <>
                    <IconButton sx={{ color: '#191970' }} onClick={() => handleEditClick(params.row)}>
                        <EditIcon />
                    </IconButton>
                    <IconButton sx={{ color: '#FF0000' }} onClick={() => handleDeleteClick(params.row.id)}>
                        <DeleteIcon />
                    </IconButton>
                    <IconButton
                        sx={{ color: 'green' }}
                        onClick={() => {
                            setSelectedEmpresa(params.row);
                            setOpenVantagemModal(true);
                        }}
                    >
                        <AddCircleIcon />
                    </IconButton>
                </>
            ),
        },
    ];

    const cadEmpresa = () => {
        navigate('/cadastrarEmpresa');
    };

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
                        rows={empresas}
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
                        Tem certeza que deseja deletar esta empresa?
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

            <Dialog open={openEditModal} onClose={handleEditClose}>
                <DialogTitle>Editar '{selectedEmpresa?.nome}'</DialogTitle>
                <DialogContent>
                    <TextField
                        fullWidth
                        label="Nome da Empresa"
                        value={editedNome}
                        onChange={(e) => setEditedNome(e.target.value)}
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleEditClose} color="primary">
                        Cancelar
                    </Button>
                    <Button onClick={handleSaveEdit} color="primary">
                        Salvar
                    </Button>
                </DialogActions>
            </Dialog>

            <ModalAddVantagem
                open={openVantagemModal}
                onClose={() => setOpenVantagemModal(false)}
                empresaId={selectedEmpresa?.id}
            />

        </div>
    );
};

export default VizualizarEmpresa;
