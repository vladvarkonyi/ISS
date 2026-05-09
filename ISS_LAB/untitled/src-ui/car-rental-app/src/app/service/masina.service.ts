import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Masina } from '../models/masina.model';

@Injectable({
  providedIn: 'root'
})
export class MasinaService {
  private apiUrl = 'http://localhost:8080/api/masini';

  constructor(private http: HttpClient) {}

  getMasiniDisponibile(): Observable<Masina[]> {
    return this.http.get<Masina[]>(`${this.apiUrl}/disponibile`);
  }

  getToateMasinile(): Observable<Masina[]> {
    return this.http.get<Masina[]>(this.apiUrl);
  }

  addMasina(masina: Masina): Observable<Masina> {
    return this.http.post<Masina>(this.apiUrl, masina);
  }

  updateMasina(id: number, masina: Masina): Observable<Masina> {
    return this.http.put<Masina>(`${this.apiUrl}/${id}`, masina);
  }

  deleteMasina(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

}
