export interface Material {
  id: number;
  nombre: string;
  descripcion: string;
  tipo: string;
  serial: string;
  numeroInterno: string;
  precio: number;
  fechaCompra: Date;
  fechaVenta: Date;
  estado: string; // activo,
  ciudad: Ciudad;
}
export interface Ciudad {
  idciudad: number;
  nombre: string;
  departamento: string;
}