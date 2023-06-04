import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {PredictionResponse} from "../../models/prediction-response";
import {PredictionDto} from "../../models/prediction-dto";
import {catchError, map, tap} from "rxjs/operators";
import {EMPTY, Observable} from "rxjs";
import {MessageService} from "primeng/api";
import {SavedPredictionDto} from "../../models/saved-prediction-dto";
import {TranslateService} from "@ngx-translate/core";

@Injectable({
  providedIn: 'root'
})
export class PredictionService {

  constructor(private http: HttpClient, private translate: TranslateService, private messageService: MessageService) { }

  predict(request: PredictionDto): Observable<PredictionResponse> {
    let requestPath: string = environment.apiUrl + `/api/prediction/predict`;
    return this.http.post<PredictionResponse>(requestPath, request).pipe(
      map((response: PredictionResponse) => {
        return response;
      }),
      catchError((err: HttpErrorResponse) => {
        this.messageService.add({severity:'error', summary: 'Error', detail: err.error.message});
        return EMPTY;
      })
    );
  }

  save(request: PredictionDto){
    let requestPath: string = environment.apiUrl + `/api/prediction`;
    return this.http.post<any>(requestPath, request).pipe(
      tap((res: any) => {
        this.messageService.add({severity:'success', summary: 'Success', detail: res.message})
      }),
      catchError((err: HttpErrorResponse) => {
        console.log(err)
        this.messageService.add({severity:'error', summary: 'Error', detail: err.error.message});
        throw err;
      })
    );
  }

  delete(id: string){
    let requestPath: string = environment.apiUrl + `/api/prediction`;
    return this.http.delete<any>(requestPath + `/${id}`, )
      .pipe(
        tap((res: any) => {
          this.messageService.add({severity:'success', summary: 'Success', detail: res.message})
        }),
        catchError((err: HttpErrorResponse) => {
          this.messageService.add({severity:'error', summary: 'Error', detail: err.error.message});
          throw err;
        })
      );
  }

  update(request: SavedPredictionDto){
    let requestPath: string = environment.apiUrl + `/api/prediction`;
    return this.http.put<any>(requestPath, request).pipe(
      tap((res: any) => {
        this.messageService.add({severity:'success', summary: 'Success', detail: res.message})
      }),
      catchError((err: HttpErrorResponse) => {
        this.messageService.add({severity:'error', summary: 'Error', detail: err.error.message});
        throw err;
      })
    );
  }

  getAllPredictions(): Observable<SavedPredictionDto[]>{
    let requestPath: string = environment.apiUrl + `/api/prediction`;
    return this.http.get<SavedPredictionDto[]>(requestPath)
      .pipe(
        map((response: SavedPredictionDto[]) => {
          return response;
        }),
        catchError((err: any) => {
          this.messageService.add({severity:'error', summary: 'Error', detail: err.error.message});
          throw err;
        }),
      );
  }

  getPredictionById(id: string) {
    let requestPath: string = environment.apiUrl + `/api/prediction/${id}`;
    return this.http.get<PredictionDto>(requestPath)
      .pipe(
        map((response: PredictionDto) => {
          return response;
        }),
        catchError((err: HttpErrorResponse) => {
          this.messageService.add({severity:'error', summary: 'Error', detail: err.error.message});
          return EMPTY;
        }),
      );
  }
}
